package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Adulto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdultoCrudRepository extends CrudRepository<Adulto, Integer> {
    public List<Adulto> findByUsername(String correo);
    public List<Adulto> findByIdCuidadorOrderByNombre(Integer idCuidador);
}
