package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PictogramCarerRepository {
    List<PictogramCarer> getAll();
    ResponseEntity<PictogramCarer> getById(int id);
    List<PictogramCarer> getAllByIdSubcategory(int id);
    ResponseEntity<PictogramCarer> save(PictogramCarer pictogram);
    ResponseEntity<ResponsePersonalized> delete(int id);
    ResponseEntity<PictogramCarer> update(int id, PictogramCarer pictogram);
}
