package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.PictogramHelp;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramHelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class PictogramHelpServices {
    @Autowired
    private PictogramHelpRepository pictogram;

    public ResponseEntity<?> getAll() {
        return pictogram.getAll();
    }
    public ResponseEntity<PictogramHelp> getById(int id) {
        return pictogram.getById(id);
    }
    public ResponseEntity<?> save(PictogramHelp pictogramHelp) {
        return pictogram.save(pictogramHelp);
    }
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return pictogram.delete(id);
    }
    public ResponseEntity<PictogramHelp> update(int id, PictogramHelp pictogramHelp) {
        return pictogram.update(id, pictogramHelp);
    }
}
