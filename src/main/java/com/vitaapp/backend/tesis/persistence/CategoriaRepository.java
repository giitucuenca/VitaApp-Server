package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.ImageSubcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoriaRepository implements CategoryRepository {
    @Autowired
    private CategoriaCrudRepository crud;
    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private ImagenCategoriaRepository imagen;

    @Override
    public List<Category> getAll() {
        // TODO Auto-generated method stub
        List<Categoria> categorias = crud.findByMostrarOrderByNombreAsc(true);
        List<Category> categories = categorias.stream().map(categoria -> {
            Category category = mapper.toCategory(categoria);
            category.setColor(categoria.getColor().getColor());
            return category;
        }).collect(Collectors.toList());
        return categories;
    }

    @Override
    public ResponseEntity<?> getByIdCategory(int id) {
        return crud.findById(id)
                .map(categoria -> {
                    Category category =  mapper.toCategory(categoria);
                    category.setColor(categoria.getColor().getColor());
                    return new ResponseEntity(category, HttpStatus.OK);
                }
                ).orElseGet(() -> {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe la categoria");
            response.getErrors().add("No existe la categoria");
            ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            return responseEntity;
        });
    }

    @Override
    public ResponseEntity<ResponsePersonalized> save(Category category) {
        // TODO Auto-generated method stub
        category.setShow(true);
        if(category.getImages() != null && !category.getImages().isEmpty()) {

                Categoria categoria = crud.save(mapper.toCategoria(category));
                category.getImages().forEach(image -> {
                    image.setCategoryId(categoria.getIdCategoria());
                    imagen.save(image);
                });
                return new ResponseEntity<>( new ResponsePersonalized(200, "Categoria Agregada Correctamente"), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(new ResponsePersonalized(404, "Debe ingresar al menos una imagen"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return crud.findById(id).map(categoria -> {
            categoria.setMostrar(false);
            crud.save(categoria);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Eliminada Correctamente"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponsePersonalized(404, "Categoria no encontrada"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponsePersonalized> updateCategory(Integer id, Category category) {
        Optional<Categoria> categoriaData = crud.findById(id);
        Categoria categoria = mapper.toCategoria(category);
        if (categoriaData.isPresent()) {
            Categoria _categoria = categoriaData.get();
            _categoria.setNombre(categoria.getNombre());
            _categoria.setDescripcion(categoria.getDescripcion());
            _categoria.setImagenUrl(categoria.getImagenUrl());
            _categoria.setIdColor(categoria.getIdColor());
            // imagen.delete(_categoria.getIdCategoria());
            List<ImageCategory> images = imagen.getImagesByCategoryId(id);
            images.forEach(image -> {
                boolean delete = true;
                for(ImageCategory imagePut : category.getImages()) {
                    if(imagePut.getImageId() != null && imagePut.getImageId() == image.getImageId()) {
                        delete = false;
                        break;
                    }
                }
                if (delete) {
                    imagen.deleteById(image.getImageId());
                }
            });
            category.getImages().forEach(image -> {
                image.setCategoryId(_categoria.getIdCategoria());
                imagen.save(image);
            });
            mapper.toCategory(crud.save(_categoria));
            ResponsePersonalized response = new ResponsePersonalized();
            response.setCode(200);
            response.setMessage("Categoria Modificada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
