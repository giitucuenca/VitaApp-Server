package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Administrador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministradorCrudRepository extends CrudRepository<Administrador, Integer> {
    List<Administrador> findByCorreoOrderByCorreoAsc(String correo);
}
