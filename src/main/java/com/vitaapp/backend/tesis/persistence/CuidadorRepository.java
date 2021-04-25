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

import java.util.*;

@Repository
public class CuidadorRepository implements CarerRepository {
    @Autowired
    CarerMapper mapper;

    @Autowired
    CuidadorCrudRepository crud;

    @Override
    public ResponseEntity<?> save(Carer carer) {
        if(existsByEmail(carer.getEmail())) {
            Map<String, List<String>> errors = new HashMap<>();
            List<String> messages = new ArrayList<>();
            messages.add("El email ya existe");
            errors.put("Messages", messages);
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(mapper.toCarer(crud.save(mapper.toCuidador(carer))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return crud.findById(id)
                .map(carer -> {
                    crud.deleteById(id);
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
        return crud.findById(id)
                .map(cuidador -> new ResponseEntity<>(mapper.toCarer(cuidador), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Carer getByEmail(String email) {
        return crud.findByCorreoOrderByCorreoAsc(email).stream().map(cuidador -> {
            return mapper.toCarer(cuidador);
        }).findAny().orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return !this.crud.findByCorreoOrderByCorreoAsc(email).isEmpty();
    }


}
