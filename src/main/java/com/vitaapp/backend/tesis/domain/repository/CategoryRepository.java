package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

public interface CategoryRepository {
	List<Category> getAll();
	Optional<Category> getByIdCategory(int id);
	ResponseEntity<ResponsePersonalized> save(Category category);
	ResponseEntity<ResponsePersonalized> delete(Integer id);
	ResponseEntity<ResponsePersonalized> updateCategory(Integer id, Category category);
}
