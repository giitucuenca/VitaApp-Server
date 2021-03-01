package com.vitaapp.backend.tesis.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.getAll();
	}
	
	public Optional<Category> getByIdCategory(int id) {
		return categoryService.getByIdCategory(id);
	}
	public Category save(Category category) {
		return categoryService.save(category);
	}
	
}
