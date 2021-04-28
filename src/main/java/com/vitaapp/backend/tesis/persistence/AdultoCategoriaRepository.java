package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyCategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.AdultoCategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.ElderlyCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdultoCategoriaRepository implements ElderlyCategoryRepository {
    @Autowired
    private AdultoCategoriaCrudRepository crud;

    @Autowired
    private ElderlyCategoryMapper mapper;

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
    public ResponseEntity<?> updateList(List<ElderlyCategory> elderlyCategoryList) {
        //crud.deleteByIdIdAdulto(elderlyId);
        return saveList(elderlyCategoryList);
    }

    public void deleteByIdAdulto(Integer elderlyId) {
        crud.deleteByIdIdAdulto(elderlyId);
    }
}
