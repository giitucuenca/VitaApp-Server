package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyCategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.AdultoCategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryCarerMapper;
import com.vitaapp.backend.tesis.persistence.mapper.ElderlyCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdultoCategoriaRepository implements ElderlyCategoryRepository {
    @Autowired
    private AdultoCategoriaCrudRepository crud;

    @Autowired
    private ElderlyCategoryMapper mapper;

    @Autowired
    private CategoryCarerMapper categoryMapper;

    @Override
    public ResponseEntity<?> saveList(List<ElderlyCategory> elderlyCategoryList) {
        List<AdultoCategoriaPersonalizada> lista = mapper.toAdultoCategoriaList(elderlyCategoryList);
        ResponsePersonalized response = new ResponsePersonalized(200, "Lista agregada correctamente");
        response.setData(mapper.toElderlyCategoryList((List<AdultoCategoriaPersonalizada>) crud.saveAll(lista)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getElderlyCategoryByElderlyId(Integer elderlyId) {
        List<AdultoCategoriaPersonalizada> lista  = crud.findByIdIdAdulto(elderlyId);
        return new ResponseEntity<>(mapper.toElderlyCategoryList(lista), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateList(Integer elderlyId, List<ElderlyCategory> elderlyCategoryList) {
        crud.deleteByIdIdAdulto(elderlyId);
        return saveList(elderlyCategoryList);
    }

    @Override
    public ResponseEntity<?> getCategoriesByElderlyId(Integer elderlyId) {
        List<AdultoCategoriaPersonalizada> adultoCategorias = crud.findByIdIdAdulto(elderlyId);
        List<CategoriaPersonalizada> categorias = new ArrayList<>();
        adultoCategorias.forEach(adultoCategoriaPersonalizada ->  {
            categorias.add(adultoCategoriaPersonalizada.getCategoriaPersonalizada());
        });
        return new ResponseEntity<>(categoryMapper.toCategories(categorias), HttpStatus.OK);
    }

    @Transactional
    public void deleteByIdAdulto(Integer elderlyId) {
        crud.deleteByIdIdAdulto(elderlyId);
    }

    @Transactional
    public void deleteByIdCategoria(Integer categoryId) {
        crud.deleteByIdIdCategoria(categoryId);
    }
}
