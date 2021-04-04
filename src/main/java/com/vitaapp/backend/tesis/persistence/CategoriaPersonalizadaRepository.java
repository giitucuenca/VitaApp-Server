package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CategoryCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.CuidadorCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaPersonalizadaRepository implements CategoryCarerRepository {

    @Autowired
    private CategoriaPersonalizadaCrudRepository categoriaCrudRepository;

    @Autowired
    private CuidadorCrudRepository cuidadorCrud;

    @Autowired
    private CategoryCarerMapper mapper;


    @Override
    public List<CategoryCarer> getAll() {
        return mapper.toCategories((List<CategoriaPersonalizada>) categoriaCrudRepository.findAll());
    }

    @Override
    public ResponseEntity<List<CategoryCarer>> getCategoryByCarerId(int carerId) {
        Optional<Cuidador> cuidador = cuidadorCrud.findById(carerId);
        if (cuidador.isPresent()) {
            return new ResponseEntity<>(mapper.toCategories(categoriaCrudRepository.findByIdCuidadorOrderByNombreAsc(carerId)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<CategoryCarer> getByIdCategory(int id) {
        return categoriaCrudRepository
                .findById(id)
                .map(categoriaPersonalizada -> new ResponseEntity<>(mapper.toCategory(categoriaPersonalizada), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponsePersonalized> save(CategoryCarer category) {
        try {
            categoriaCrudRepository.save(mapper.toCategoria(category));
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria creada"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponsePersonalized(404, "Cateogoria no creada"),
                    HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return categoriaCrudRepository.findById(id).map(categoria -> {
            categoriaCrudRepository.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Eliminada"), HttpStatus.OK);
        }).orElseGet(() -> {
            return new ResponseEntity<>(new ResponsePersonalized(404, "Categoria no encontrada"), HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<ResponsePersonalized> updateCategory(Integer id, CategoryCarer category) {
        Optional<CategoriaPersonalizada> categoriaData = categoriaCrudRepository.findById(id);
        CategoriaPersonalizada categoria = mapper.toCategoria(category);
        if (categoriaData.isPresent()) {
            CategoriaPersonalizada _categoria = categoriaData.get();
            _categoria.setNombre(categoria.getNombre());
            _categoria.setDescripcion(categoria.getDescripcion());
            _categoria.setImagenUrl(categoria.getImagenUrl());
            _categoria.setColor(categoria.getColor());
            categoriaCrudRepository.save(_categoria);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Modificada Correctamente"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
