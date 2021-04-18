package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.SubcategoryCarerService;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carer/subcategory")
public class SubcategoryCarerController {
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
    public ResponseEntity<SubcategoryCarer> getByIdSubcategory(@PathVariable int id) {
        return subcategoryCarer.getByIdSubcategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<SubcategoryCarer> save(@RequestBody SubcategoryCarer subcategory){
        return subcategoryCarer.save(subcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(@PathVariable int id){
        return subcategoryCarer.deleteSubcategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryCarer> updateSubcategory(@PathVariable int id, @RequestBody SubcategoryCarer subcategory){
        return subcategoryCarer.updateSubcategory(id, subcategory);
    }
}
