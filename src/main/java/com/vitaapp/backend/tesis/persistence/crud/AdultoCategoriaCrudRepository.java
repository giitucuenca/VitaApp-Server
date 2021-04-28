package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizadaPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdultoCategoriaCrudRepository extends CrudRepository<AdultoCategoriaPersonalizada, AdultoCategoriaPersonalizadaPK> {
    List<AdultoCategoriaPersonalizada> findByIdIdAdulto(Integer idAdulto);
    List<AdultoCategoriaPersonalizada> findByIdIdCategoriaPersonalizada(Integer idAdulto);
    List<AdultoCategoriaPersonalizada> deleteByIdIdAdulto(Integer idAdulto);
}
