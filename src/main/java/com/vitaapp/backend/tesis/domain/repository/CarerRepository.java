package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface CarerRepository  {
    ResponseEntity<?> save(Carer admin);
    ResponseEntity<ResponsePersonalized> delete(Integer id);
    ResponseEntity<Carer> getByIdCarer(Integer id);
    Carer getByEmail(String email);
    boolean existsByEmail(String email);
}