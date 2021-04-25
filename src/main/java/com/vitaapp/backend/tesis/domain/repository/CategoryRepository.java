package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

public interface CategoryRepository {
	List<Category> getAll();
	ResponseEntity<?> getByIdCategory(int id);
	ResponseEntity<?> save(Category category);
	ResponseEntity<?> delete(Integer id);
	ResponseEntity<?> updateCategory(Integer id, Category category);
}
