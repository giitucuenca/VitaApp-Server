package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyudaPersonalizado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PictogramaAyudaPersonalizadaCrudRepository
                extends CrudRepository<PictogramaAyudaPersonalizado, Integer> {
        List<PictogramaAyudaPersonalizado> findByIdAyudaOrderByPosicionAsc(Integer idAyuda);

        @Query(value = "SELECT MAX(posicion) FROM PictogramaAyudaPersonalizado WHERE idAyuda = ?1")
        Integer findPosicionMaxima(Integer idAyuda);
}
