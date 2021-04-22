package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Scholarity;
import com.vitaapp.backend.tesis.domain.services.ScholarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scholarity")
public class ScholarityController {
    @Autowired
    ScholarityService service;

    @GetMapping("/any/all")
    public List<Scholarity> getAll() {
        return service.getAll();
    }

}
