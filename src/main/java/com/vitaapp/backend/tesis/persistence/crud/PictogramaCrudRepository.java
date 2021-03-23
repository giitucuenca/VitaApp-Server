package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictogramaCrudRepository extends CrudRepository<Pictograma, Integer> {
    List<Pictograma> findByIdSubcategoriaOrderByNombreAsc(Integer idSubcategoria);
}
