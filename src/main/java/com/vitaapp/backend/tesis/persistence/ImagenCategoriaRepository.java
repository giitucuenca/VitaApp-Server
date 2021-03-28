package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.persistence.crud.ImagenCategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenCategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImagenCategoriaRepository {
    @Autowired
    private ImagenCategoriaCrudRepository imagenCrud;

    @Autowired
    private ImagenCategoriaMapper imagenMapper;

    public ImageCategory save(ImageCategory image) {
        return imagenMapper.toImageCategory(imagenCrud.save(imagenMapper.toImagenCategoria(image)));
    }

    public ImageCategory put(ImageCategory image) {
        Optional<ImagenCategoria> imagenData = imagenCrud.findById(image.getImageId());
        ImagenCategoria imagen = imagenMapper.toImagenCategoria(image);
        if(imagenData.isPresent()) {
            ImagenCategoria _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdCategoria(imagen.getIdCategoria());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return imagenMapper.toImageCategory(_imagen);
        } else {
            return null;
        }
    }
}
