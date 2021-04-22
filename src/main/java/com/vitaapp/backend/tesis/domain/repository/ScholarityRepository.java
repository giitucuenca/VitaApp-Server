package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.Scholarity;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScholarityRepository {
    List<Scholarity> getAll();
}
