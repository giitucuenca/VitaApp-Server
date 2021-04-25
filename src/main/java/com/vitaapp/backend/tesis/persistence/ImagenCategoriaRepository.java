package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.repository.ImageRepository;
import com.vitaapp.backend.tesis.persistence.crud.ImagenCategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenCategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("category")
public class ImagenCategoriaRepository implements ImageRepository {
    @Autowired
    private ImagenCategoriaCrudRepository crud;

    @Autowired
    private ImagenCategoriaMapper mapper;

    public ImageCategory save(ImageCategory image) {
        if(image.getImageId() != null && crud.findById(image.getImageId()).isPresent()) {
            return put(image);
        } else {
            ImagenCategoria imagen = mapper.toImagenCategoria(image);
            return mapper.toImageCategory(crud.save(imagen));
        }
    }



    public List<ImageCategory> getImagesByCategoryId(Integer id) {
        return mapper.toImagesCategories(crud.findByIdCategoria(id));
    }


    public ImageCategory put(ImageCategory image) {
        Optional<ImagenCategoria> imagenData = crud.findById(image.getImageId());
        ImagenCategoria imagen = mapper.toImagenCategoria(image);
        if(imagenData.isPresent()) {
            ImagenCategoria _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdCategoria(imagen.getIdCategoria());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return mapper.toImageCategory(_imagen);
        } else {
            return null;
        }
    }

    public void delete(Integer categoryId) {
        crud.findByIdCategoria(categoryId).forEach(imagen -> {
            crud.delete(imagen);
        });
    }

    public void deleteById(Integer imageId) {
        crud.deleteById(imageId);
    }

    @Override
    public ResponseEntity<?> getImageByFatherId(int id) {
        return new ResponseEntity<>(getImagesByCategoryId(id), HttpStatus.OK);
    }
}
