package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Administrador;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CuidadorCrudRepository extends CrudRepository<Cuidador, Integer> {
    List<Cuidador> findByCorreoOrderByCorreoAsc(String correo);
}
