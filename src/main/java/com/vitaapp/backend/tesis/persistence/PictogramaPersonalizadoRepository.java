package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PictogramaPersonalizadoRepository implements PictogramCarerRepository {
    @Autowired
    private PictogramaPersonalizadaCrudRepository crud;

    @Autowired
    private PictogramCarerMapper mapper;

    @Autowired
    private SubcategoriaPersonalizadaCrudRepository crudSubcategory;



    @Override
    public List<PictogramCarer> getAll() {
        List<PictogramaPersonalizado> pictogramas = (List< PictogramaPersonalizado>) crud.findAll();
        return addColor(pictogramas);
    }


    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<PictogramaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramCarer pictogram = mapper.toPictogram(pictograma.get());
            pictogram.setColor(pictograma.get().getSubcategoriaPersonalizada().getCategoriaPersonalizada().getColor());
            return new ResponseEntity<>(pictogram, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe el pictograma");
            response.getErrors().add("No existe el pictograma");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<PictogramCarer> getAllByIdSubcategory(int id, String email) {
        if(!email.substring(0, 6).equals("admin-")) {
            String emailCarer = emailCarer(id);
            if(!emailCarer.equals(email.substring(6))) {
                throw new RuntimeException("No tiene permisos para acceder a esta información");
            }
        }
        List<PictogramaPersonalizado> pictogramas = crud.findByIdSubcategoriaPersonalizadaOrderByPosicionAsc(id);
        return pictogramas.stream().map(pictograma -> {

            PictogramCarer pictogram = mapper.toPictogram(pictograma);
            pictogram.setColor(pictograma.getSubcategoriaPersonalizada().getCategoriaPersonalizada().getColor());
            return pictogram;
        }).collect(Collectors.toList());
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
    public ResponseEntity<?> update(int id, PictogramCarer pictogram) {
        Optional<PictogramaPersonalizado> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaPersonalizado _pictograma = pictograma.get();
            _pictograma.setNombre(pictogram.getName());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setIdSubcategoriaPersonalizada(pictogram.getSubcategoryId());
            _pictograma.setPosicion(pictogram.getPosition());
            return new ResponseEntity<>(mapper.toPictogram(crud.save(_pictograma)), HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro el pictograma");
            response.getErrors().add("No se encontro el pictograma");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
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

    public List<PictogramCarer> addColor(List<PictogramaPersonalizado> pictogramas) {
       return pictogramas.stream().map(pictograma -> {
            PictogramCarer pictogram = mapper.toPictogram(pictograma);
            pictogram.setColor(pictograma.getSubcategoriaPersonalizada().getCategoriaPersonalizada().getColor());
            return pictogram;
        }).collect(Collectors.toList());
    }

    public String emailCarer(Integer subcategoryId) {
        Optional<SubcategoriaPersonalizada> subcategoria = crudSubcategory.findById(subcategoryId);
        if(subcategoria.isPresent()) {
            return subcategoria.get().getCategoriaPersonalizada().getCuidador().getCorreo();
        } else {
            throw new RuntimeException("No existe la subcategoria");
        }
    }
}
