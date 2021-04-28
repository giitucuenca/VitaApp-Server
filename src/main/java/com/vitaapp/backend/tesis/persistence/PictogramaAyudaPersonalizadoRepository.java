package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.PictogramHelperCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramHelperCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaAyudaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyudaPersonalizado;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramHelperCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PictogramaAyudaPersonalizadoRepository implements PictogramHelperCarerRepository {
    @Autowired
    PictogramaAyudaPersonalizadaCrudRepository crud;

    @Autowired
    PictogramHelperCarerMapper mapper;


    @Override
    public List<PictogramHelperCarer> getAll() {
        List<PictogramaAyudaPersonalizado> pictogramas = (List< PictogramaAyudaPersonalizado>) crud.findAll();
        return addColor(pictogramas);
    }


    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<PictogramaAyudaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramHelperCarer pictogram = mapper.toPictogram(pictograma.get());
            pictogram.setColor(pictograma.get().getAyuda().getColor());
            return new ResponseEntity<>(pictogram, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe el pictograma");
            response.getErrors().add("No existe el pictograma");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<PictogramHelperCarer> getAllByIdHelper(int id) {
        List<PictogramaAyudaPersonalizado> pictogramas = crud.findByIdAyudaOrderByPosicionAsc(id);
        return addColor(pictogramas);
    }

    @Override
    public ResponseEntity<PictogramHelperCarer> save(PictogramHelperCarer pictogram) {
        Integer posicion = crud.findPosicionMaxima(pictogram.getHelperId());
        if(posicion == null) {
            posicion = 0;
        } else {
            posicion += 1;
        }
        pictogram.setPosition(posicion);
        PictogramaAyudaPersonalizado pictogramaAyuda = mapper.toPictograma(pictogram);
        return new ResponseEntity<>(mapper.toPictogram(crud.save(pictogramaAyuda)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveList(List<PictogramHelperCarer> pictograms) {

        if (!pictograms.isEmpty()) {
            Integer posicion = crud.findPosicionMaxima(pictograms.get(0).getHelperId());
            if(posicion == null) {
                posicion = 0;
            } else {
                posicion += 1;
            }
            Integer id = pictograms.get(0).getHelperId();
            for(PictogramHelperCarer pictogram: pictograms) {
                if(pictogram.getHelperId() == id) {
                    pictogram.setPosition(posicion);
                } else {
                    ResponsePersonalized response = new ResponsePersonalized(404, "La lista debe pertenecer a la misma ayuda");
                    response.getErrors().add("La lista debe pertenecer a la misma ayuda");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
                posicion++;
            }
        }

        List<PictogramaAyudaPersonalizado> pictogramas = (List<PictogramaAyudaPersonalizado>) crud.saveAll(mapper.toPictogramas(pictograms));
        ResponsePersonalized response = new ResponsePersonalized(200, "Pictogramas creados correctamente");
        response.setData(mapper.toPictograms(pictogramas));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public ResponseEntity<?> updatePosition(List<PictogramHelperCarer> pictograms) {
        Integer helperId = -1;
        if(pictograms.size() > 0) {
            helperId = pictograms.get(0).getHelperId();
        }
            for(PictogramHelperCarer pictogram : pictograms) {
                if(helperId != pictogram.getHelperId()) {
                    throw new RuntimeException("La lista debe pertenecer a la misma ayuda");
                }
                updateList(pictogram.getPictogramCarerId(), pictogram);
            }
        ResponsePersonalized response = new ResponsePersonalized(200, "Pictogramas modificados correctamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override

    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return crud.findById(id).map(pictograma -> {
            crud.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Pictograma eliminado correctamente."), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponsePersonalized(404, "No se encontro el pictograma a eliminar"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> update(int id, PictogramHelperCarer pictogram) {
        Optional<PictogramaAyudaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaAyudaPersonalizado _pictograma = pictograma.get();
            _pictograma.setNombre(pictogram.getName());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setIdAyuda(pictogram.getHelperId());
            _pictograma.setPosicion(pictogram.getPosition());
            return new ResponseEntity<>(mapper.toPictogram(crud.save(_pictograma)), HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro el pictograma");
            response.getErrors().add("No se encontro el pictograma");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public boolean updateList(int id, PictogramHelperCarer pictogram) {
        Optional<PictogramaAyudaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaAyudaPersonalizado _pictograma = pictograma.get();
            _pictograma.setNombre(pictogram.getName());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setIdAyuda(pictogram.getHelperId());
            _pictograma.setPosicion(pictogram.getPosition());
            mapper.toPictogram(crud.save(_pictograma));
            return true;
        } else {
            throw new RuntimeException("Error no existe el pictograma");
        }
    }



    public List<PictogramHelperCarer> addColor(List<PictogramaAyudaPersonalizado> pictogramas) {
        return pictogramas.stream().map(pictograma -> {
            PictogramHelperCarer pictogram = mapper.toPictogram(pictograma);
            pictogram.setColor(pictograma.getAyuda().getColor());
            return pictogram;
        }).collect(Collectors.toList());
    }

}
