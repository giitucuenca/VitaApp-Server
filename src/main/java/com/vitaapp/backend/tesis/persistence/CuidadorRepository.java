package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.CuidadorCrudRepository;
import com.vitaapp.backend.tesis.persistence.mapper.CarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CuidadorRepository implements CarerRepository {

    @Autowired
    CarerMapper carerMapper;

    @Autowired
    CuidadorCrudRepository cuidadorCrud;

    @Override
    public ResponseEntity<ResponsePersonalized> save(Carer carer) {
        if(cuidadorCrud.findByCorreoOrderByCorreoAsc(carer.getEmail()).isEmpty()) {
            ResponsePersonalized response = new ResponsePersonalized(200,"cuidador creado correctamente." );
            cuidadorCrud.save(carerMapper.toCuidador(carer));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "El email ya existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return cuidadorCrud.findById(id)
                .map(carer -> {
                    cuidadorCrud.deleteById(id);
                    ResponsePersonalized mensaje = new ResponsePersonalized(200,"Cuidador eliminado correctamento.");
                    return new ResponseEntity<>(mensaje, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    ResponsePersonalized mensaje = new ResponsePersonalized(404, "Cuidador no encontrado.");
                    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public ResponseEntity<Carer> getByIdCarer(Integer id) {
        return cuidadorCrud.findById(id)
                .map(cuidador -> new ResponseEntity<>(carerMapper.toCarer(cuidador), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Carer getByEmail(String email) {
        return cuidadorCrud.findByCorreoOrderByCorreoAsc(email).stream().map(cuidador -> {
            return carerMapper.toCarer(cuidador);
        }).findAny().orElse(null);
    }
}
