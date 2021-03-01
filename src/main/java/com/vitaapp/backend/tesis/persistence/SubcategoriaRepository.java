package com.vitaapp.backend.tesis.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;

@Repository
public class SubcategoriaRepository {
	@Autowired
	private SubcategoriaCrudRepository subcategoriaCrudRepository;
	
	public List<Subcategoria> getAllSubcategorias() {
		return (List<Subcategoria>) subcategoriaCrudRepository.findAll();
	}
	
	public List<Subcategoria> getByIdCategoria(Integer id) {
		return (List<Subcategoria>) subcategoriaCrudRepository.findByIdCategoriaOrderByNombreAsc(id);
	}
	
	public Optional<Subcategoria> getByIdSubcategoria(Integer id) {
		return subcategoriaCrudRepository.findById(id);
	}
	
	public Subcategoria save(Subcategoria subcategoria) {
		return subcategoriaCrudRepository.save(subcategoria);
	}
	
}
