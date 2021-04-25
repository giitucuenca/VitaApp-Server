package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImageSubcategory;
import com.vitaapp.backend.tesis.domain.repository.ImageCategoryRepository;
import com.vitaapp.backend.tesis.domain.repository.ImageRepository;
import com.vitaapp.backend.tesis.persistence.crud.ImagenSubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenSubcategoria;
import com.vitaapp.backend.tesis.persistence.mapper.ImagenSubcategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("subcategory")
public class ImagenSubcategoriaRepository implements ImageRepository, ImageCategoryRepository {
    @Autowired
    private ImagenSubcategoriaCrudRepository crud;

    @Autowired
    private ImagenSubcategoriaMapper mapper;

    public ImageSubcategory save(ImageSubcategory image) {
        if(image.getImageId() != null && crud.findById(image.getImageId()).isPresent()) {
           return put(image);
        } else {
            return mapper.toImageSubcategory(crud.save(mapper.toImagenSubcategoria(image)));
        }
    }

    public ImageSubcategory put(ImageSubcategory image) {
        Optional<ImagenSubcategoria> imagenData = crud.findById(image.getImageId());
        ImagenSubcategoria imagen = mapper.toImagenSubcategoria(image);
        if(imagenData.isPresent()) {
            ImagenSubcategoria _imagen = imagenData.get();
            _imagen.setIdImagen(imagen.getIdImagen());
            _imagen.setIdSubcategoria(imagen.getIdSubcategoria());
            _imagen.setNombre(imagen.getNombre());
            _imagen.setImagenUrl(imagen.getImagenUrl());
            return mapper.toImageSubcategory(_imagen);
        } else {
            return null;
        }
    }

    public void delete(Integer subcategoryId) {
        crud.findByIdSubcategoria(subcategoryId).forEach(imagen -> {
            crud.delete(imagen);
        });
    }

    public void deleteById(Integer imageId) {
        crud.deleteById(imageId);
    }

    public List<ImageSubcategory> getImagesBySubcategoryId(Integer id) {
        return mapper.toImagesSubcategories(crud.findByIdSubcategoria(id));
    }

    @Override
    public ResponseEntity<?> getImageByFatherId(int id) {
        return new ResponseEntity<>(getImagesBySubcategoryId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getImagesByCategoryId(int id) {
        return new ResponseEntity<>(mapper.toImagesSubcategories(crud.findBySubcategoriaCategoriaIdCategoria(id)), HttpStatus.OK);
    }
}
