package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyRepository;
import com.vitaapp.backend.tesis.domain.services.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/older")
public class ElderlyController {
    @Autowired
    private ElderlyService elderly;

    @PostMapping("/register")
    public ResponseEntity<ResponsePersonalized> save(@RequestBody Elderly older){
        return elderly.save(older);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable Integer id){
        return elderly.delete(id);
    }

    @GetMapping("/carer/{id}")
    public ResponseEntity<List<Elderly>> getByIdCarer(Integer id){
        return elderly.getByIdCarer(id);
    }

    @GetMapping("/email/{email}")
    public Elderly getByEmail(String email){
        return elderly.getByEmail(email);
    }
}
