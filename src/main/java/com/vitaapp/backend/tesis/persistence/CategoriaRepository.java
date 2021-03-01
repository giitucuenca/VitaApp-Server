package com.vitaapp.backend.tesis.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.repository.CategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryMapper;

@Repository
public class CategoriaRepository implements CategoryRepository{
	@Autowired
	private CategoriaCrudRepository  categoriaCrudRepository;
	@Autowired
	private CategoryMapper mapper;
	/*
	public List<Categoria> getAllCategorias() {
		return (List<Categoria>) categoriaCrudRepository.findAll();
	}
	
	public Optional<Categoria> getCategoriaById(Integer id) {
		return categoriaCrudRepository.findById(id);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaCrudRepository.save(categoria);
	} */

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return mapper.toCategories((List<Categoria>)categoriaCrudRepository.findAll());
	}

	@Override
	public Optional<Category> getByIdCategory(int id) {
		return categoriaCrudRepository.findById(id).map(categoria -> mapper.toCategory(categoria));
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return mapper.toCategory(categoriaCrudRepository.save(mapper.toCategoria(category)));
	}
	
}
