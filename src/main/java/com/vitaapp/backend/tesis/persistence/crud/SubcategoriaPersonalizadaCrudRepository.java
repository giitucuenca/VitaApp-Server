package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubcategoriaPersonalizadaCrudRepository extends CrudRepository<SubcategoriaPersonalizada, Integer> {
    List<SubcategoriaPersonalizada> findByIdCategoriaPersonalizadaOrderByNombreAsc(Integer idCategoria);

}
