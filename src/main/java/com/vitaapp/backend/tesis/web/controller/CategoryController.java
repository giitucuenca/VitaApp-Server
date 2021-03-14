package com.vitaapp.backend.tesis.web.controller;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public List<Category> getAll() {
		return categoryService.getAll();
	}

	@GetMapping("/{id}")
	public Optional<Category> getByIdCategory(@PathVariable Integer id) {
		return categoryService.getByIdCategory(id);
	}

	@PostMapping("/add")
	public ResponseEntity<String> save(@RequestBody Category category) {
		return categoryService.save(category);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Message> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Message> delete(@PathVariable Integer id) {
		return  categoryService.delete(id);
	}
}
