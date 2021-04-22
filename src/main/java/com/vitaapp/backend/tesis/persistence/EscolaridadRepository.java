package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Scholarity;
import com.vitaapp.backend.tesis.domain.repository.ScholarityRepository;
import com.vitaapp.backend.tesis.persistence.crud.EscolaridadCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.NivelEscolaridad;
import com.vitaapp.backend.tesis.persistence.mapper.ScholarityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EscolaridadRepository implements ScholarityRepository {
    private ScholarityMapper mapper;
    private EscolaridadCrudRepository crud;

    @Override
    public List<Scholarity> getAll() {
        return mapper.toShocholarityLevels((List<NivelEscolaridad>) crud.findAll());
    }
}
