package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

public interface CarerRepository {
    ResponseEntity<ResponsePersonalized> save(Carer admin);
    ResponseEntity<ResponsePersonalized> delete(Integer id);
    ResponseEntity<Carer> getByIdCarer(Integer id);
    Carer getByEmail(String email);
}