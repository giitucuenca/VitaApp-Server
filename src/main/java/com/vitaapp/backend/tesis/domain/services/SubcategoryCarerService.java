package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryCarerService {

    @Autowired
    private SubcategoryCarerRepository subcategoryCarer;

    public List<SubcategoryCarer> getAll() {
        return subcategoryCarer.getAll();
    }

    public List<SubcategoryCarer> getByCategory(int categoryId) {
        return subcategoryCarer.getByCategory(categoryId);
    }

    public ResponseEntity<?> getByIdSubcategory(int id) {
        return subcategoryCarer.getByIdSubcategory(id);
    }

    public ResponseEntity<?> save(SubcategoryCarer subcategory) {
        return subcategoryCarer.save(subcategory);
    }

    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return subcategoryCarer.deleteSubcategory(id);
    }

    public ResponseEntity<?> updateSubcategory(int id, SubcategoryCarer subcategory) {
        return subcategoryCarer.updateSubcategory(id, subcategory);
    }

    public ResponseEntity<?> saveList(List<SubcategoryCarer> subcategories) {
        return subcategoryCarer.saveList(subcategories);
    }
}
