package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictogramaPersonalizadaCrudRepository extends CrudRepository<PictogramaPersonalizado, Integer> {
    List<PictogramaPersonalizado> findByIdSubcategoriaPersonalizadaOrderByNombreAsc(Integer idSubcategoriaPersonalizada);
}
