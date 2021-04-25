package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.SubcategoryCarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carer/subcategory")
public class SubcategoryCarerController {

    @Autowired
    private SubcategoryCarerService subcategoryCarer;

    @GetMapping("/all")
    public List<SubcategoryCarer> getAll() {
        return subcategoryCarer.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<SubcategoryCarer> getByCategory(@PathVariable int categoryId) {
        return subcategoryCarer.getByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdSubcategory(@PathVariable int id) {
        return subcategoryCarer.getByIdSubcategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@Valid @RequestBody SubcategoryCarer subcategory){
        return subcategoryCarer.save(subcategory);
    }

    @PostMapping("/add/list")
    public ResponseEntity<?> saveList(@Valid @RequestBody List<SubcategoryCarer> subcategories) {
        return subcategoryCarer.saveList(subcategories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(@PathVariable int id){
        return subcategoryCarer.deleteSubcategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubcategory(@PathVariable int id, @RequestBody SubcategoryCarer subcategory){
        return subcategoryCarer.updateSubcategory(id, subcategory);
    }
}
