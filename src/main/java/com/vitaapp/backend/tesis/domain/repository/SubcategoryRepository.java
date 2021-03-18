package com.vitaapp.backend.tesis.domain.repository;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

public interface SubcategoryRepository {
	List<Subcategory> getAll();
	List<Subcategory> getByCategory(int categoryId);
	Optional<Subcategory> getByIdSubcategory(int id);
	ResponseEntity<Subcategory> save(Subcategory subcategory);
	ResponseEntity<ResponsePersonalized> deleteSubcategory(int id);
	ResponseEntity<Subcategory> updateSubcategory(int id, Subcategory subcategory);
}
