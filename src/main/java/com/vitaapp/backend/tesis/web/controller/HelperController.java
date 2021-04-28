package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Helper;
import com.vitaapp.backend.tesis.domain.services.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carer/helper")
public class HelperController {
    @Autowired
    private HelperService service;

    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody Helper helper) {
        return service.save(helper);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody Helper helper) {
        return service.update(id, helper);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
