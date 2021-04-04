package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ElderlyRepository {
    ResponseEntity<ResponsePersonalized> save(Elderly older);
    ResponseEntity<ResponsePersonalized> delete(Integer id);
    ResponseEntity<List<Elderly>> getByIdCarer(Integer id);
    Elderly getByEmail(String email);
}
