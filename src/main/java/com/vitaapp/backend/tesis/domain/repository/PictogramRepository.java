package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PictogramRepository {
    List<Pictogram> getAll();
    Optional<Pictogram> getById(int id);
    List<Pictogram> getAllByIdSubcategory(int id);
    ResponseEntity<Pictogram> save(Pictogram pictogram);
    ResponseEntity<ResponsePersonalized> delete(int id);
    ResponseEntity<Pictogram> update(int id, Pictogram pictogram);
}
