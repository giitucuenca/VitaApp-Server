package com.vitaapp.backend.tesis.domain.services;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	public List<Category> getAll() {
		return categoryRepository.getAll();
	}
	public Optional<Category> getByIdCategory(int id) {
		return categoryRepository.getByIdCategory(id);
	}
	public ResponseEntity<Message> delete(Integer id) {
		return categoryRepository.delete(id);
	}
	public ResponseEntity<String> save(Category category) {
		return categoryRepository.save(category);
	}
	public ResponseEntity<Message> updateCategory(Integer id, Category category) {
		return categoryRepository.updateCategory(id, category);
	}
	
	
}
