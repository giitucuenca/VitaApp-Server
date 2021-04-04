package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaPersonalizadaCrudRepository extends CrudRepository<CategoriaPersonalizada, Integer> {
    List<CategoriaPersonalizada> findByIdCuidadorOrderByNombreAsc(int idCuidador);
}
