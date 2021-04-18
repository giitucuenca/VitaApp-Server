package com.vitaapp.backend.tesis.domain;

import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<SubcategoryCarer> getByIdSubcategory(int id) {
        return subcategoryCarer.getByIdSubcategory(id);
    }

    public ResponseEntity<SubcategoryCarer> save(SubcategoryCarer subcategory) {
        return subcategoryCarer.save(subcategory);
    }

    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return subcategoryCarer.deleteSubcategory(id);
    }

    public ResponseEntity<SubcategoryCarer> updateSubcategory(int id, SubcategoryCarer subcategory) {
        return subcategoryCarer.updateSubcategory(id, subcategory);
    }
}
