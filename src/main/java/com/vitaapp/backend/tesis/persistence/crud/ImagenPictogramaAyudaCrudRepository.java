package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.ImagenPictogramaAyuda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImagenPictogramaAyudaCrudRepository extends CrudRepository<ImagenPictogramaAyuda, Integer> {
    List<ImagenPictogramaAyuda> findByIdPictograma(Integer idPictograma);
}
