package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import com.vitaapp.backend.tesis.domain.services.ElderlyCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carer/elderly-category")
public class ElderlyCategoryController {
    @Autowired
    private ElderlyCategoryService service;

    @GetMapping("/elderly/{id}")
    public ResponseEntity<?> getElderlyCategoryByElderlyId(@PathVariable Integer id) {
        return service.getElderlyCategoryByElderlyId(id);
    }

    @PostMapping("/list/{id}")
    public ResponseEntity<?> updateList( @RequestBody List<ElderlyCategory> elderlyCategoryList) {
        return service.updateList( elderlyCategoryList);
    }

}
