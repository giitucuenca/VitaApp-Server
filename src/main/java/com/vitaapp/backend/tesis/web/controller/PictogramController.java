package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.PictogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pictogram")
public class PictogramController {
    @Autowired
    private PictogramService pictogramService;

    @GetMapping("/any/all")
    public ResponseEntity<?> getAll() {
        return pictogramService.getAll();
    }

    @GetMapping("/any/subcategory/{id}")
    public List<Pictogram> getAllByIdSubcategory(@PathVariable int id) {
        return pictogramService.getAllByIdSubcategory(id);
    }
    @GetMapping("/any/{id}")
    public ResponseEntity<Pictogram> getById(@PathVariable int id) {
        return pictogramService.getById(id);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> save(@Valid @RequestBody Pictogram pictogram) {
        return  pictogramService.save(pictogram);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable int id) {
        return pictogramService.delete(id);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Pictogram> update(@Valid @PathVariable int id, @RequestBody Pictogram pictogram) {
        return pictogramService.update(id, pictogram);
    }

    @GetMapping("/any/category/{id}")
    public ResponseEntity<?> getAllByIdCategory(@PathVariable  int id) {
        return pictogramService.getAllByIdCategory(id);
    }

}
