package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PictogramaPersonalizadoRepository implements PictogramCarerRepository {
    @Autowired
    private PictogramaPersonalizadaCrudRepository crud;

    @Autowired
    private PictogramCarerMapper mapper;


    @Override
    public List<PictogramCarer> getAll() {
        return mapper.toPictograms((List< PictogramaPersonalizado>) crud.findAll());
    }

    @Override
    public ResponseEntity<PictogramCarer> getById(int id) {
        return crud.findById(id)
                .map(pictograma -> new ResponseEntity<>(mapper.toPictogram(pictograma), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<PictogramCarer> getAllByIdSubcategory(int id) {
        return mapper.toPictograms(crud.findByIdSubcategoriaPersonalizadaOrderByPosicionAsc(id));
    }

    @Override
    public ResponseEntity<PictogramCarer> save(PictogramCarer pictogram) {
        Integer posicion = crud.findPosicionMaxima(pictogram.getSubcategoryId());
        if(posicion == null) {
            posicion = 0;
        } else {
            posicion += 1;
        }
        pictogram.setPosition(posicion);
        return new ResponseEntity<>(mapper.toPictogram(crud.save(mapper.toPictograma(pictogram))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveList(List<PictogramCarer> pictograms) {

        if (!pictograms.isEmpty()) {
            Integer posicion = crud.findPosicionMaxima(pictograms.get(0).getSubcategoryId());
            if(posicion == null) {
                posicion = 0;
            } else {
                posicion += 1;
            }
            Integer id = pictograms.get(0).getSubcategoryId();
            for(PictogramCarer pictogram: pictograms) {
                if(pictogram.getSubcategoryId() == id) {
                    pictogram.setPosition(posicion);
                } else {
                    ResponsePersonalized response = new ResponsePersonalized(404, "La lista debe pertenecer a la misma subcategoria");
                    response.getErrors().add("La lista debe pertenecer a la misma subcategoria");
                    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                }
                posicion++;
            }
        }


        List<PictogramaPersonalizado> pictogramas = (List<PictogramaPersonalizado>) crud.saveAll(mapper.toPictogramas(pictograms));
        ResponsePersonalized response = new ResponsePersonalized(200, "Pictogramas creados correctamente");
        response.setData(mapper.toPictograms(pictogramas));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatePosition(List<PictogramCarer> pictograms) {
        pictograms.forEach(pictogram -> {
            update(pictogram.getPictogramCarerId(), pictogram);
        });
        ResponsePersonalized response = new ResponsePersonalized(200, "Pictogramas creados correctamente");
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
    public ResponseEntity<PictogramCarer> update(int id, PictogramCarer pictogram) {
        Optional<PictogramaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaPersonalizado _pictograma = pictograma.get();
            _pictograma.setNombre(pictogram.getName());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setIdSubcategoriaPersonalizada(pictogram.getSubcategoryId());
            _pictograma.setPosicion(pictogram.getPosition());
            return new ResponseEntity<>(mapper.toPictogram(crud.save(_pictograma)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void deletePictogramsByCategoryId(Integer idCategoria) {
        List<PictogramaPersonalizado> pictogramas = (List<PictogramaPersonalizado>) crud.findBySubcategoriaPersonalizadaCategoriaPersonalizadaIdCategoriaPersonalizada(idCategoria);
        pictogramas.forEach(pictograma -> {
            crud.deleteById(pictograma.getIdPictogramaPersonalizado());
        });
    }

    public void deletePictogramsBySubcategoryId(Integer idSubcategoria) {
        List<PictogramaPersonalizado> pictogramas = (List<PictogramaPersonalizado>) crud.findBySubcategoriaPersonalizadaIdSubcategoriaPersonalizada(idSubcategoria);
        pictogramas.forEach(pictograma -> {
            crud.deleteById(pictograma.getIdPictogramaPersonalizado());
        });
    }
}
