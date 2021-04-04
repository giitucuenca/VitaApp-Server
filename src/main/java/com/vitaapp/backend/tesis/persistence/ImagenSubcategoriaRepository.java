package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.ImageSubcategory;
import com.vitaapp.backend.tesis.persistence.crud.ImagenCategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.ImagenSubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.entity.ImagenSubcategoria;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenCategoriaMapper;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenSubcategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImagenSubcategoriaRepository {
    @Autowired
    private ImagenSubcategoriaCrudRepository imagenCrud;

    @Autowired
    private ImagenSubcategoriaMapper imagenMapper;

    public ImageSubcategory save(ImageSubcategory image) {
        return imagenMapper.toImageSubcategory(imagenCrud.save(imagenMapper.toImagenSubcategoria(image)));
    }

    public ImageSubcategory put(ImageSubcategory image) {
        Optional<ImagenSubcategoria> imagenData = imagenCrud.findById(image.getImageId());
        ImagenSubcategoria imagen = imagenMapper.toImagenSubcategoria(image);
        if(imagenData.isPresent()) {
            ImagenSubcategoria _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdSubcategoria(imagen.getIdSubcategoria());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return imagenMapper.toImageSubcategory(_imagen);
        } else {
            return null;
        }
    }

    public void delete(Integer subcategoryId) {
        imagenCrud.findByIdSubcategoria(subcategoryId).forEach(imagen -> {
            imagenCrud.delete(imagen);
        });
    }
}
