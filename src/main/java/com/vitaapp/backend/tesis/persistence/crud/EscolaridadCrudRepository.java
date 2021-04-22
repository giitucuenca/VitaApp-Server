package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.NivelEscolaridad;
import org.springframework.data.repository.CrudRepository;

public interface EscolaridadCrudRepository extends CrudRepository<NivelEscolaridad, Integer> {
}
