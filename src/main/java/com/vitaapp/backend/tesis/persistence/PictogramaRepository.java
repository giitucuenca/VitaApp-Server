package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PictogramaRepository implements PictogramRepository {
    @Autowired
    private PictogramaCrudRepository crud;

    @Autowired
    private PictogramMapper mapper;

    @Autowired
    private ImagenPictogramaRepository imagen;

    @Override
    public ResponseEntity<?> getAll() {
        List<Pictograma> pictogramas = (List<Pictograma>) crud.findAll();
        List<Pictogram> pictograms = pictogramas.stream().map(pictograma -> {
            Pictogram pictogram = mapper.toPictogram(pictograma);
            pictogram.setColor(pictograma.getSubcategoria().getCategoria().getColor().getColor());
            return pictogram;
        }).collect(Collectors.toList());
        return  new ResponseEntity<>(pictograms, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pictogram> getById(int id) {
        Optional<Pictograma> pictograma = crud.findById(id);
        if(pictograma.isPresent()) {
            Pictograma _pictograma = pictograma.get();
            Pictogram pictogram = mapper.toPictogram(_pictograma);
            pictogram.setColor(_pictograma.getSubcategoria().getCategoria().getColor().getColor());
            return new ResponseEntity<>(pictogram, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Pictogram> getAllByIdSubcategory(int id) {
        List<Pictograma> pictogramas = crud.findByIdSubcategoriaOrderByNombreAsc(id);
        List<Pictogram> pictograms = mapper.toPictograms(pictogramas);
        if(!pictogramas.isEmpty()) {
            String color = pictogramas.get(0).getSubcategoria().getCategoria().getColor().getColor();
            pictograms.forEach(pictogram -> pictogram.setColor(color));
        }
        return pictograms;
    }

    @Override
    public ResponseEntity<?> getAllByIdCategory(int id) {
        List<Pictograma> pictogramas = crud.findBySubcategoriaCategoriaIdCategoria(id);
        List<Pictogram> pictograms = mapper.toPictograms(pictogramas);
        if(!pictogramas.isEmpty()) {
            String color = pictogramas.get(0).getSubcategoria().getCategoria().getColor().getColor();
            pictograms.forEach(pictogram -> pictogram.setColor(color));
        }
        return new ResponseEntity<>(pictograms, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(Pictogram pictogram) {
        if(pictogram.getImages() != null && !pictogram.getImages().isEmpty()) {

               Pictogram _pictogram = mapper
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
    public ResponseEntity<Pictogram> update(int id, Pictogram pictogram) {
        Optional<Pictograma> pictogramaData = crud.findById(id);
        Pictograma pictograma = mapper.toPictograma(pictogram);
        if(pictogramaData.isPresent()) {
            Pictograma _pictograma = pictogramaData.get();
            _pictograma.setNombre(pictograma.getNombre());
            _pictograma.setIdSubcategoria(pictograma.getIdSubcategoria());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            // imagen.delete(pictogram.getPictogramId());
            List<ImagePictogram> images = imagen.getImagesByPictogramId(id);
            images.forEach(image -> {
                boolean delete = true;
                for(ImagePictogram imagePut : pictogram.getImages()) {
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
