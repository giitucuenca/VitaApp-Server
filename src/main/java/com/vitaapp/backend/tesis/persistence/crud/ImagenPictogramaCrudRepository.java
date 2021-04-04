package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImagenPictogramaCrudRepository extends CrudRepository<ImagenPictograma, Integer> {
    List<ImagenPictograma> findByIdPictograma(Integer idPictograma);
}


