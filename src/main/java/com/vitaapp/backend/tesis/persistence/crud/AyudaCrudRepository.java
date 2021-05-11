package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Ayuda;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AyudaCrudRepository extends CrudRepository<Ayuda, Integer> {
    public List<Ayuda> findAllByOrderByNombreAsc();
    public List<Ayuda> findByCuidadorIdCuidadorOrderByNombreAsc(Integer idCuidador);
}
