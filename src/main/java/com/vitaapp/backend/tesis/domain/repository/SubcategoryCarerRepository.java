package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface SubcategoryCarerRepository {
    List<SubcategoryCarer> getAll();
    List<SubcategoryCarer> getByCategory(int categoryId, String email);
    ResponseEntity<?> getByIdSubcategory(int id);
    ResponseEntity<?> save(SubcategoryCarer subcategory);
    ResponseEntity<ResponsePersonalized> deleteSubcategory(int id);
    ResponseEntity<?> updateSubcategory(int id, SubcategoryCarer subcategory);
    ResponseEntity<?> saveList(List<SubcategoryCarer> subcategories);
}
