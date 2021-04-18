package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramCarerRepository;
import com.vitaapp.backend.tesis.domain.services.PictogramCarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carer/pictogram")
public class PictogramCarerController {
    @Autowired
    private PictogramCarerService pictogramCarer;

    @GetMapping("/all")
    public List<PictogramCarer> getAll(){
        return pictogramCarer.getAll();
    }
    @GetMapping("/subcategory/{id}")
    public List<PictogramCarer> getAllByIdSubcategory(@PathVariable int id){
        return pictogramCarer.getAllByIdSubcategory(id);
    }
    @PostMapping("/add")
    public ResponseEntity<PictogramCarer> save(@RequestBody PictogramCarer pictogram){
        return pictogramCarer.save(pictogram);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable int id){
        return pictogramCarer.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictogramCarer> update(@PathVariable int id, @RequestBody PictogramCarer pictogram){
        return pictogramCarer.update(id, pictogram);
    }

}
