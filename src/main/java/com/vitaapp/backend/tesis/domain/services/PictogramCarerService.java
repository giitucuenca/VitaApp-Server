package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramCarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class PictogramCarerService {

    @Autowired
    private PictogramCarerRepository pictogramCarer;


    public List<PictogramCarer> getAll() {
     return pictogramCarer.getAll();
    }

    public ResponseEntity<?> getById(int id) {
        return pictogramCarer.getById(id);
    }

    public List<PictogramCarer> getAllByIdSubcategory(int id) {
        return pictogramCarer.getAllByIdSubcategory(id);
    }

    public ResponseEntity<?> save(PictogramCarer pictogram) {
        return pictogramCarer.save(pictogram);
    }


    public ResponseEntity<?> updatePosition(List<PictogramCarer> pictograms) {
        return pictogramCarer.updatePosition(pictograms);
    }

    public ResponseEntity<?> saveList(List<PictogramCarer> pictograms) {
        return pictogramCarer.saveList(pictograms);
    }

    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return pictogramCarer.delete(id);
    }

    public ResponseEntity<?> update(int id, PictogramCarer pictogram) {
        return pictogramCarer.update(id, pictogram);
    }

}
