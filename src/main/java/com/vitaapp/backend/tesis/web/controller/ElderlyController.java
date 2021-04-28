package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/elderly")
public class ElderlyController {
    @Autowired
    private ElderlyService service;

    @PostMapping("/register")
    public ResponseEntity<ResponsePersonalized> save(@Valid  @RequestBody Elderly older){
        return service.save(older);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @GetMapping("/carer/{id}")
    public ResponseEntity<List<Elderly>> getByIdCarer(@PathVariable Integer id){
        return service.getByIdCarer(id);
    }

    @GetMapping("/username/{id}")
    public Elderly getByUsername(@PathVariable String id){
        return service.getByUsername(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Integer id, @RequestBody Elderly elderly) {
        return service.update(id, elderly);
    }
}
