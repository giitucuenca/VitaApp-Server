package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Category;
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

@Repository
public class CategoriaRepository implements CategoryRepository {
    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;
    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private ImagenCategoriaRepository imagen;

    @Override
    public List<Category> getAll() {
        // TODO Auto-generated method stub
        return mapper.toCategories(categoriaCrudRepository.findByMostrarOrderByNombreAsc(true));
    }

    @Override
    public Optional<Category> getByIdCategory(int id) {
        return categoriaCrudRepository.findById(id).map(categoria -> mapper.toCategory(categoria));
    }

    @Override
    public ResponseEntity<ResponsePersonalized> save(Category category) {
        // TODO Auto-generated method stub
        category.setShow(true);
        if(category.getImages() != null && !category.getImages().isEmpty()) {
            try {
                Categoria categoria = categoriaCrudRepository.save(mapper.toCategoria(category));
                category.getImages().forEach(image -> {
                    image.setCategoryId(categoria.getIdCategoria());
                    imagen.save(image);
                });
                return new ResponseEntity<>( new ResponsePersonalized(200, "Categoria Agregada Correctamente"), HttpStatus.CREATED);
            } catch (Exception exception) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new ResponsePersonalized(404, "Tiene que ingresar al menos una imagen"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return categoriaCrudRepository.findById(id).map(categoria -> {
            categoria.setMostrar(false);
            categoriaCrudRepository.save(categoria);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Eliminada Correctamente"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponsePersonalized(404, "Categoria no encontrada"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponsePersonalized> updateCategory(Integer id, Category category) {
        Optional<Categoria> categoriaData = categoriaCrudRepository.findById(id);
        Categoria categoria = mapper.toCategoria(category);
        if (categoriaData.isPresent()) {
            Categoria _categoria = categoriaData.get();
            _categoria.setNombre(categoria.getNombre());
            _categoria.setDescripcion(categoria.getDescripcion());
            _categoria.setImagenUrl(categoria.getImagenUrl());
            _categoria.setIdColor(categoria.getIdColor());
            imagen.delete(_categoria.getIdCategoria());
            category.getImages().forEach(image -> {
                image.setCategoryId(_categoria.getIdCategoria());
                imagen.save(image);
            });
            mapper.toCategory(categoriaCrudRepository.save(_categoria));
            ResponsePersonalized response = new ResponsePersonalized();
            response.setCode(200);
            response.setMessage("Categoria Modificada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
