package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/all")
    public List<Subcategory> getAll(){
        return subcategoryService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Subcategory> getByCategory(@PathVariable int categoryId){
        return subcategoryService.getByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getByIdSubcategory(@PathVariable int id){
        return  subcategoryService.getByIdSubcategory(id);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Subcategory> save(@RequestBody Subcategory subcategory){
        return subcategoryService.save(subcategory);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ResponsePersonalized> deleteSubcategry(@PathVariable  int id){
        return subcategoryService.deleteSubcategory(id);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable int id, @RequestBody Subcategory subcategory){
        return subcategoryService.updateSubcategory(id, subcategory);
    }
}
