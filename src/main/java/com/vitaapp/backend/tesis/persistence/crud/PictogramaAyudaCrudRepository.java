package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyuda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictogramaAyudaCrudRepository extends CrudRepository<PictogramaAyuda, Integer> {
    public List<PictogramaAyuda> findByMostrarOrderByNombre(boolean mostrar);
}