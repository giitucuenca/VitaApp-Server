package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.ImagenSubcategoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImagenSubcategoriaCrudRepository extends CrudRepository<ImagenSubcategoria, Integer> {
    List<ImagenSubcategoria> findByIdSubcategoria(Integer idSubcategoria);
    List<ImagenSubcategoria> findBySubcategoriaCategoriaIdCategoria(Integer idCategoria);
}
