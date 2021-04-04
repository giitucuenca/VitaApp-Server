package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyRepository;
import com.vitaapp.backend.tesis.persistence.crud.AdultoCrudRepository;
import com.vitaapp.backend.tesis.persistence.mapper.ElderlyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdultoRepository implements ElderlyRepository {

    @Autowired
    private ElderlyMapper elderlyMapper;

    @Autowired
    private AdultoCrudRepository adultoCrud;

    @Override
    public ResponseEntity<ResponsePersonalized> save(Elderly older) {
        elderlyMapper.toElderly(adultoCrud.save(elderlyMapper.toAdulto(older)));
        return new ResponseEntity<>(new ResponsePersonalized(200, "Adulto creado correctamente.") , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return adultoCrud.findById(id).map(adulto-> {
            adultoCrud.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Adulto Eliminado Correctamente"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Elderly>> getByIdCarer(Integer id) {
        return new ResponseEntity<>(elderlyMapper.toElderlies(adultoCrud.findByIdCuidadorOrderByNombre(id)), HttpStatus.OK);
    }

    @Override
    public Elderly getByEmail(String email) {
        return adultoCrud.findByCorreo(email).stream().map(adulto -> elderlyMapper.toElderly(adulto)).findAny().orElse(null);
    }
}
