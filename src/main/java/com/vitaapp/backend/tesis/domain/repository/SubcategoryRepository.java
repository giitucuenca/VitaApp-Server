package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Subcategory;

public interface SubcategoryRepository {
	List<Subcategory> getAll();
	Optional<List<Subcategory>> getByCategory(int categoryId);
	Optional<Subcategory> getByIdSubcategory(int id);
	Subcategory save(Subcategory subcategory);
}
