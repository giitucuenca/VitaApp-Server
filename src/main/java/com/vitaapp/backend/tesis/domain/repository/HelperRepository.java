package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Helper;
import org.springframework.http.ResponseEntity;

public interface HelperRepository {
    ResponseEntity<?> save(Helper helper);
    ResponseEntity<?> getAll();
    ResponseEntity<?> findById(Integer id);
    ResponseEntity<?> update(Integer id, Helper helper);
    ResponseEntity<?> delete(Integer id);
    ResponseEntity<?> getByCarerId(Integer carerId);

}
