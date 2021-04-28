package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.PictogramHelperCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramHelpRepository;
import com.vitaapp.backend.tesis.domain.repository.PictogramHelperCarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PictogramHelperCarerService {
    @Autowired
    private PictogramHelperCarerRepository repository;

    public List<PictogramHelperCarer> getAll() {
        return repository.getAll();
    }

    public ResponseEntity<?> getById(int id) {
        return repository.getById(id);
    }

    public List<PictogramHelperCarer> getAllByIdHelper(int id) {
        return repository.getAllByIdHelper(id);
    }

    public ResponseEntity<?> save(PictogramHelperCarer pictogram) {
        return repository.save(pictogram);
    }


    public ResponseEntity<?> updatePosition(List<PictogramHelperCarer> pictograms) {
        return repository.updatePosition(pictograms);
    }

    public ResponseEntity<?> saveList(List<PictogramHelperCarer> pictograms) {
        return repository.saveList(pictograms);
    }

    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return repository.delete(id);
    }

    public ResponseEntity<?> update(int id, PictogramHelperCarer pictogram) {
        return repository.update(id, pictogram);
    }
}
