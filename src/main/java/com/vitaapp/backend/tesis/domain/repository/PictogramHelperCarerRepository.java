package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.PictogramHelperCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PictogramHelperCarerRepository {
    List<PictogramHelperCarer> getAll();
    ResponseEntity<?> getById(int id);
    List<PictogramHelperCarer> getAllByIdHelper(int id, String email);
    ResponseEntity<?> save(PictogramHelperCarer pictogram);
    ResponseEntity<?> saveList(List<PictogramHelperCarer> pictograms);
    ResponseEntity<?> updatePosition(List<PictogramHelperCarer> pictograms);
    ResponseEntity<ResponsePersonalized> delete(int id);
    ResponseEntity<?> update(int id, PictogramHelperCarer pictogram);
}
