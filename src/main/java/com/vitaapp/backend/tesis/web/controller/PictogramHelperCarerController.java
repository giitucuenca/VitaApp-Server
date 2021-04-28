package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.PictogramHelperCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.PictogramHelperCarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carer/pictogram-helper")
public class PictogramHelperCarerController {
    @Autowired
    private PictogramHelperCarerService pictogramCarer;

    @GetMapping("/all")
    public List<PictogramHelperCarer> getAll(){
        return pictogramCarer.getAll();
    }

    @GetMapping("/helper/{id}")
    public List<PictogramHelperCarer> getAllByIdHelper(@PathVariable int id){
        return pictogramCarer.getAllByIdHelper(id);
    }
    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody PictogramHelperCarer pictogram){
        return pictogramCarer.save(pictogram);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return pictogramCarer.getById(id);
    }

    @PostMapping("/add/list")
    public ResponseEntity<?> saveList(@Valid @RequestBody List<PictogramHelperCarer> pictograms){
        return pictogramCarer.saveList(pictograms);
    }

    @PutMapping("/update-position")
    public ResponseEntity<?> updatePosition(@Valid @RequestBody List<PictogramHelperCarer> pictograms) {
        return pictogramCarer.updatePosition(pictograms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable int id){
        return pictogramCarer.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PictogramHelperCarer pictogram){
        return pictogramCarer.update(id, pictogram);
    }
}
