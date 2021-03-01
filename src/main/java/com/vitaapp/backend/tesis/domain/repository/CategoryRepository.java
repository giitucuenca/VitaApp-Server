package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Category;

public interface CategoryRepository {
	List<Category> getAll();
	Optional<Category> getByIdCategory(int id);
	Category save(Category category);
}
