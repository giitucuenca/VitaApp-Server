package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryCarerRepository {
    List<CategoryCarer> getAll();
    ResponseEntity<List<CategoryCarer>> getCategoryByCarerId(int carerId);
    ResponseEntity<CategoryCarer> getByIdCategory(int id);
    ResponseEntity<ResponsePersonalized> save(CategoryCarer category);
    ResponseEntity<ResponsePersonalized> delete(Integer id);
    ResponseEntity<ResponsePersonalized> updateCategory(Integer id, CategoryCarer category);
}
