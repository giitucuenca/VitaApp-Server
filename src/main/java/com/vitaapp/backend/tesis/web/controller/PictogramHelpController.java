package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.PictogramHelp;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.PictogramHelpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pictogram-help")
public class PictogramHelpController {
    @Autowired
    private PictogramHelpServices pictogramService;

    @GetMapping("/any/all")
    public ResponseEntity<?> getAll() {
        return pictogramService.getAll();
    }


    @GetMapping("/any/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return pictogramService.getById(id);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> save(@Valid @RequestBody PictogramHelp pictogram) {
        return  pictogramService.save(pictogram);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return pictogramService.delete(id);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable int id, @RequestBody PictogramHelp pictogram) {
        return pictogramService.update(id, pictogram);
    }


}
