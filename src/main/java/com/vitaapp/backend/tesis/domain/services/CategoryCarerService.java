package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CategoryCarerRepository;
import com.vitaapp.backend.tesis.web.controller.CategoryCarerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryCarerService {
    @Autowired
    private CategoryCarerRepository categoryCarer;

    public List<CategoryCarer> getAll() {
        return categoryCarer.getAll();
    }
    public ResponseEntity<List<CategoryCarer>> getCategoryByCarerId(int carerId) {
        return categoryCarer.getCategoryByCarerId(carerId);
    }
    public ResponseEntity<CategoryCarer> getByIdCategory(int id) {
        return categoryCarer.getByIdCategory(id);
    }
    public ResponseEntity<ResponsePersonalized> save(CategoryCarer category) {
        return categoryCarer.save(category);
    }
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return categoryCarer.delete(id);
    }
    public ResponseEntity<ResponsePersonalized> updateCategory(Integer id, CategoryCarer category) {
        return categoryCarer.updateCategory(id, category);
    }

}
