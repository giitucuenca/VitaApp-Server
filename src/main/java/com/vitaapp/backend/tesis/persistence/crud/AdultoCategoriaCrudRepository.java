package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizadaPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdultoCategoriaCrudRepository extends CrudRepository<AdultoCategoriaPersonalizada, AdultoCategoriaPersonalizadaPK> {
    List<AdultoCategoriaPersonalizada> findByIdIdAdulto(Integer idAdulto);
    List<AdultoCategoriaPersonalizada> findByIdIdCategoriaPersonalizada(Integer idAdulto);
    @Modifying
    @Query("delete from AdultoCategoriaPersonalizada where id_adulto = ?1")
    void deleteByIdIdAdulto(Integer idAdulto);

    @Modifying
    @Query("delete from AdultoCategoriaPersonalizada where id_categoria_personalizada = ?1")
    void deleteByIdIdCategoria(Integer idCategoria);
}
