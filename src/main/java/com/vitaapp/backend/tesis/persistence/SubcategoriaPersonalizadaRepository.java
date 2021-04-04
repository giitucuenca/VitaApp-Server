package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.SubcategoryCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubcategoriaPersonalizadaRepository implements SubcategoryCarerRepository {
    @Autowired
    private SubcategoriaPersonalizadaCrudRepository subcategoriaCrudRepository;

    @Autowired
    private SubcategoryCarerMapper subcategoryMapper;

    @Override
    public List<SubcategoryCarer> getAll() {
        return subcategoryMapper.toSubcategories((List<SubcategoriaPersonalizada>) subcategoriaCrudRepository.findAll());
    }

    @Override
    public List<SubcategoryCarer> getByCategory(int categoryId) {
        return subcategoryMapper.toSubcategories(subcategoriaCrudRepository.findByIdCategoriaPersonalizadaOrderByNombreAsc(categoryId));
    }

    @Override
    public Optional<SubcategoryCarer> getByIdSubcategory(int id) {
        return subcategoriaCrudRepository.findById(id).map(subcategoria -> subcategoryMapper.toSubcategory(subcategoria));
    }

    @Override
    public ResponseEntity<SubcategoryCarer> save(SubcategoryCarer subcategory) {
        return new ResponseEntity<>(subcategoryMapper.toSubcategory(subcategoriaCrudRepository.save(subcategoryMapper.toSubcategoria(subcategory))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return subcategoriaCrudRepository.findById(id).map(subcategoria -> {
            subcategoriaCrudRepository.deleteById(id);
            return new ResponseEntity(new ResponsePersonalized(200, "Subcategoria eliminada"), HttpStatus.OK);
        }).orElse(new ResponseEntity(new ResponsePersonalized(404, "No se encontro el elemento a eliminar"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<SubcategoryCarer> updateSubcategory(int id, SubcategoryCarer subcategory) {
        Optional<SubcategoriaPersonalizada> subcategoria = subcategoriaCrudRepository.findById(id);
        if(subcategoria.isPresent()) {
            SubcategoriaPersonalizada _subcategoria = subcategoria.get();
            _subcategoria.setNombre(subcategory.getName());
            _subcategoria.setDescripcion(subcategory.getDescription());
            _subcategoria.setImagenUrl(subcategory.getImageUrl());
            _subcategoria.setIdCategoriaPersonalizada(subcategory.getCategoryId());
            subcategoriaCrudRepository.save(_subcategoria);
            return new ResponseEntity<>(getByIdSubcategory(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
            //return new ResponseEntity(new ResponsePersonalized(404, "No se encontro el elemento a eliminar"), HttpStatus.NOT_FOUND);
        }

    }
}
