package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.persistence.crud.ImagenPictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenPictogramaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImagenPictogramaRepository {
    @Autowired
    private ImagenPictogramaCrudRepository imagenCrud;

    @Autowired
    private ImagenPictogramaMapper imagenMapper;

    public ImagePictogram save(ImagePictogram image) {
        return imagenMapper.toImagePictogram(imagenCrud.save(imagenMapper.toImagenPictograma(image)));
    }

    public ImagePictogram put(ImagePictogram image) {
        Optional<ImagenPictograma> imagenData = imagenCrud.findById(image.getImageId());
        ImagenPictograma imagen = imagenMapper.toImagenPictograma(image);
        if(imagenData.isPresent()) {
            ImagenPictograma _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdPictograma(imagen.getIdPictograma());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return imagenMapper.toImagePictogram(_imagen);
        } else {
            return null;
        }
    }

    public void delete(Integer pictogramId) {
        imagenCrud.findByIdPictograma(pictogramId).forEach(imagen -> {
            imagenCrud.delete(imagen);
        });
    }
}
