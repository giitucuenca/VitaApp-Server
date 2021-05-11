package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Helper;
import com.vitaapp.backend.tesis.domain.repository.HelperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelperService {
    @Autowired
    private HelperRepository helperRepository;

    public ResponseEntity<?> save(Helper helper) {
        return helperRepository.save(helper);
    }

    public ResponseEntity<?> getAll() {
        return helperRepository.getAll();
    }

    public ResponseEntity<?> getHelperByCarerId(Integer carerId) {
        return helperRepository.getByCarerId(carerId);
    }

    public ResponseEntity<?> findById(Integer id) {
        return helperRepository.findById(id);
    }

    public ResponseEntity<?> delete(Integer id) {
        return helperRepository.delete(id);
    }

    public ResponseEntity<?> update(Integer id, Helper helper) {
        return helperRepository.update(id, helper);
    }
}
