package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.repository.ImageRepository;
import com.vitaapp.backend.tesis.persistence.crud.ImagenPictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenPictogramaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("pictogram")
public class ImagenPictogramaRepository implements ImageRepository {
    @Autowired
    private ImagenPictogramaCrudRepository crud;

    @Autowired
    private ImagenPictogramaMapper mapper;

    public ImagePictogram save(ImagePictogram image) {

        if(image.getImageId() != null && crud.findById(image.getImageId()).isPresent()) {
           return put(image);
        } else {
            return mapper.toImagePictogram(crud.save(mapper.toImagenPictograma(image)));
        }
    }

    public ImagePictogram put(ImagePictogram image) {
        Optional<ImagenPictograma> imagenData = crud.findById(image.getImageId());
        ImagenPictograma imagen = mapper.toImagenPictograma(image);
        if(imagenData.isPresent()) {
            ImagenPictograma _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdPictograma(imagen.getIdPictograma());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return mapper.toImagePictogram(_imagen);
        } else {
            return null;
        }
    }

    public List<ImagePictogram> getImagesByPictogramId(Integer id) {
        return mapper.toImagesPictograms(crud.findByIdPictograma(id));
    }

    public void delete(Integer pictogramId) {
        crud.findByIdPictograma(pictogramId).forEach(imagen -> {
            crud.delete(imagen);
        });
    }

    public void deleteById(Integer imageId) {
        crud.deleteById(imageId);
    }

    @Override
    public ResponseEntity<?> getImageByFatherId(int id) {
        return new ResponseEntity<>(getImagesByPictogramId(id), HttpStatus.OK);
    }
}
