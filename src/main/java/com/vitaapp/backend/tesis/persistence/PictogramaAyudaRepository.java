package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.ImagePictogramHelp;
import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.PictogramHelp;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramHelpRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaAyudaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyuda;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramHelpMapper;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PictogramaAyudaRepository implements PictogramHelpRepository {
    @Autowired
    private PictogramaAyudaCrudRepository crud;

    @Autowired
    private PictogramHelpMapper mapper;

    @Autowired
    private ImagenPictogramaAyudaRepository imagen;

    @Override
    public ResponseEntity<?> getAll() {
        List<PictogramaAyuda> pictogramas = (List<PictogramaAyuda>) crud.findAll();
        List<PictogramHelp> pictograms = pictogramas.stream().map(pictograma -> {
            PictogramHelp pictogram = mapper.toPictogram(pictograma);
            return pictogram;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(pictograms, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PictogramHelp> getById(int id) {
        Optional<PictogramaAyuda> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaAyuda _pictograma = pictograma.get();
            PictogramHelp pictogram = mapper.toPictogram(_pictograma);
            return new ResponseEntity<>(pictogram, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> save(PictogramHelp pictogram) {
        if(pictogram.getImages() != null && !pictogram.getImages().isEmpty()) {
            PictogramHelp _pictogram = mapper
                    .toPictogram(crud.save(mapper.toPictograma(pictogram)));
            pictogram.getImages().forEach(image -> {
                image.setPictogramId(_pictogram.getPictogramId());
                imagen.save(image);
            });
            return new ResponseEntity<>(mapper.toPictogram(crud.findById(_pictogram.getPictogramId()).get()) , HttpStatus.OK);

        } else {
            ResponsePersonalized responsePersonalized = new ResponsePersonalized(404, "Debe ingresar al menos una imagen");
            responsePersonalized.getErrors().add("Debe ingresar al menos una imagen");
            return new ResponseEntity<>(responsePersonalized, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return crud.findById(id).map(pictograma -> {
            crud.deleteById(id);
            ResponsePersonalized response = new ResponsePersonalized(200, "Pictograma Eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> {
            ResponsePersonalized response = new ResponsePersonalized(200, "Pictograma no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<PictogramHelp> update(int id, PictogramHelp pictogram) {
        Optional<PictogramaAyuda> pictogramaData = crud.findById(id);
        PictogramaAyuda pictograma = mapper.toPictograma(pictogram);
        if(pictogramaData.isPresent()) {
            PictogramaAyuda _pictograma = pictogramaData.get();
            _pictograma.setNombre(pictograma.getNombre());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setColor(pictogram.getColor());
            List<ImagePictogramHelp> images = imagen.getImagesByPictogramId(id);
            images.forEach(image -> {
                boolean delete = true;
                for(ImagePictogramHelp imagePut : pictogram.getImages()) {
                    if(imagePut.getImageId() != null && imagePut.getImageId() == image.getImageId()) {
                        delete = false;
                        break;
                    }
                }
                if (delete) {
                    imagen.deleteById(image.getImageId());
                }
            });
            pictogram.getImages().forEach(image -> {
                image.setPictogramId(_pictograma.getIdPictograma());
                imagen.save(image);
            });
            crud.save(_pictograma);
            return getById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
