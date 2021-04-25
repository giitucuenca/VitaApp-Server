package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PictogramaPersonalizadaCrudRepository extends CrudRepository<PictogramaPersonalizado, Integer> {
    List<PictogramaPersonalizado> findByIdSubcategoriaPersonalizadaOrderByPosicionAsc(Integer idSubcategoriaPersonalizada);
    List<PictogramaPersonalizado> findBySubcategoriaPersonalizadaCategoriaPersonalizadaIdCategoriaPersonalizada(Integer idCategoria);
    List<PictogramaPersonalizado> findBySubcategoriaPersonalizadaIdSubcategoriaPersonalizada(Integer idSubcategoria);
    @Query(value = "SELECT MAX(posicion) FROM PictogramaPersonalizado WHERE idSubcategoriaPersonalizada = ?1")
    Integer findPosicionMaxima(Integer idSubcategoria);
}
