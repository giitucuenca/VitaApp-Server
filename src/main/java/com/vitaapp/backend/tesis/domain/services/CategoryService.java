package com.vitaapp.backend.tesis.domain.services;

import java.util.List;
import java.util.Optional;

import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.web.security.JWTUtil;
import com.vitaapp.backend.tesis.web.security.interceptor.BearerTokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	/*@Autowired
	private BearerTokenWrapper tokenWrapper;
	@Autowired
	JWTUtil jwtUtil;
	*/

	public List<Category> getAll() {

		/*
		String tokenString = tokenWrapper.getToken();
		System.out.println("--------------------------------------");
		System.out.println(tokenString);
		System.out.println(jwtUtil.extractUsername(tokenString));
		System.out.println("--------------------------------------");

		 */
		return categoryRepository.getAll();
	}
	public ResponseEntity<?> getByIdCategory(int id) {
		return categoryRepository.getByIdCategory(id);
	}
	public ResponseEntity<?> delete(Integer id) {
		return categoryRepository.delete(id);
	}
	public ResponseEntity<?> save(Category category) {
		return categoryRepository.save(category);
	}
	public ResponseEntity<?> updateCategory(Integer id, Category category) {
		return categoryRepository.updateCategory(id, category);
	}
	
	
}
