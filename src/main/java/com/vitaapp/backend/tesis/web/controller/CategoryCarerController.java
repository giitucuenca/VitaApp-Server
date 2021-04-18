package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.CategoryCarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carer/category")
public class CategoryCarerController {

    @Autowired
    private CategoryCarerService categoryService;

    @GetMapping("/all")
    public List<CategoryCarer> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/by_carer/{carerId}")
    public ResponseEntity<List<CategoryCarer>> getCategoryByCarerId(@PathVariable int carerId) {
        return categoryService.getCategoryByCarerId(carerId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryCarer> getByIdCategory(@PathVariable int id) {
        return categoryService.getByIdCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponsePersonalized> save(@RequestBody CategoryCarer category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> updateCategory(@PathVariable Integer id, @RequestBody CategoryCarer category) {
        return categoryService.updateCategory(id, category);
    }
}
