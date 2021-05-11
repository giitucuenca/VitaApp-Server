package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.SubcategoryCarerService;
import com.vitaapp.backend.tesis.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/category/{categoryId}" )
    public List<SubcategoryCarer> getByCategory(@PathVariable int categoryId, @RequestHeader(name="Authorization") String token, @RequestParam(required = false) String email) {
        token = token.substring(7);
        String emailCarer = jwtUtil.extractUsername(token);
        if(emailCarer.substring(0, 6).equals("older-")) {
            if(email != null) {
                emailCarer = email;
            } else {
                return new ArrayList<>();
            }
        }
        return subcategoryCarer.getByCategory(categoryId, emailCarer);
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
