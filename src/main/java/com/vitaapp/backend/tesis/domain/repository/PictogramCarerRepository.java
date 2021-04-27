package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PictogramCarerRepository {
    List<PictogramCarer> getAll();
    ResponseEntity<?> getById(int id);
    List<PictogramCarer> getAllByIdSubcategory(int id);
    ResponseEntity<?> save(PictogramCarer pictogram);
    ResponseEntity<?> saveList(List<PictogramCarer> pictograms);
    ResponseEntity<?> updatePosition(List<PictogramCarer> pictograms);
    ResponseEntity<ResponsePersonalized> delete(int id);
    ResponseEntity<?> update(int id, PictogramCarer pictogram);
}
