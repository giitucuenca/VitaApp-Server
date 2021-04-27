package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.PictogramHelp;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PictogramHelpRepository {
    ResponseEntity<?> getAll();
    ResponseEntity<PictogramHelp> getById(int id);
    ResponseEntity<?> save(PictogramHelp pictogram);
    ResponseEntity<ResponsePersonalized> delete(int id);
    ResponseEntity<PictogramHelp> update(int id, PictogramHelp pictogram);
}
