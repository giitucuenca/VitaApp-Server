package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.message.Response;
import org.springframework.http.ResponseEntity;

public interface CategoryRepository {
	List<Category> getAll();
	Optional<Category> getByIdCategory(int id);
	ResponseEntity<String> save(Category category);
	ResponseEntity<Response> delete(Integer id);
	ResponseEntity<Response> updateCategory(Integer id, Category category);
}
