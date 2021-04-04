package com.vitaapp.backend.tesis.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vitaapp.backend.tesis.persistence.entity.Categoria;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer>{
    List<Categoria> findByMostrarOrderByNombreAsc(boolean mostrar);
}
