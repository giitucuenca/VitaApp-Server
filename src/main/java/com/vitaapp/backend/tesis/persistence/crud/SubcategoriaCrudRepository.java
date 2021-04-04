package com.vitaapp.backend.tesis.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;

public interface SubcategoriaCrudRepository extends CrudRepository<Subcategoria, Integer> {
	List<Subcategoria> findByIdCategoriaAndMostrarOrderByNombreAsc(Integer idCategoria, Boolean mostrar);
	List<Subcategoria> findByMostrarOrderByNombreAsc(Boolean mostrar);
}
