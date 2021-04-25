package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictogramService {

    @Autowired
    PictogramRepository pictogramRepository;
    public ResponseEntity<?> getAll() {
       return pictogramRepository.getAll();
    }
    public ResponseEntity<Pictogram> getById(int id) {
        return pictogramRepository.getById(id);
    }
    public ResponseEntity<?> save(Pictogram pictogram) {
        return  pictogramRepository.save(pictogram);
    }
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return pictogramRepository.delete(id);
    }
    public ResponseEntity<Pictogram> update(int id, Pictogram pictogram) {
        return pictogramRepository.update(id, pictogram);
    }

    public List<Pictogram> getAllByIdSubcategory(int id) {
        return pictogramRepository.getAllByIdSubcategory(id);
    }
    public ResponseEntity<?> getAllByIdCategory(int id) {
        return pictogramRepository.getAllByIdCategory(id);
    }
}
