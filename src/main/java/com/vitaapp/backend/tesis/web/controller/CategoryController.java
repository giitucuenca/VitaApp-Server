package com.vitaapp.backend.tesis.web.controller;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.services.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/any/all")
	public List<Category> getAll() {
		return categoryService.getAll();
	}

	@GetMapping("/any/{id}")
	public ResponseEntity<?> getByIdCategory(@PathVariable Integer id) {
		return categoryService.getByIdCategory(id);
	}

	@PostMapping("/admin/add")
	public ResponseEntity<?> save(@Valid @RequestBody Category category) {
		return categoryService.save(category);
	}

	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updateCategory(@Valid @PathVariable Integer id, @RequestBody Category category) {
		return categoryService.updateCategory(id, category);
	}

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return  categoryService.delete(id);
	}
}
