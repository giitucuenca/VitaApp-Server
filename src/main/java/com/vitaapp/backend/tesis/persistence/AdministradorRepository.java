package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.AdminRepository;
import com.vitaapp.backend.tesis.persistence.crud.AdministradorCrudRepository;
import com.vitaapp.backend.tesis.persistence.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AdministradorRepository implements AdminRepository {

    @Autowired
    AdminMapper mapper;

    @Autowired
    AdministradorCrudRepository crud;

    @Override
    public ResponseEntity<?> save(Admin admin) {
        if(!emailExist(admin.getEmail())) {
            return new ResponseEntity<>(mapper.toAdmin(crud.save(mapper.toAdministrador(admin))), HttpStatus.CREATED);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404,"El email ya existe.");
            response.getErrors().add("El email ya existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return crud.findById(id)
                .map(admin -> {
                   crud.deleteById(id);
                   ResponsePersonalized mensaje = new ResponsePersonalized();
                   mensaje.setMessage("Administrador eliminado correctamento.");
                   mensaje.setCode(200);
                   return new ResponseEntity<>(mensaje, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    ResponsePersonalized mensaje = new ResponsePersonalized();
                    mensaje.setMessage("Administrador no encontrado.");
                    mensaje.setCode(404);
                    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
                });
    }

    @Override
    public ResponseEntity<Admin> getByIdAdmin(Integer id) {
        return crud.findById(id)
                .map(admin -> new ResponseEntity<>(mapper.toAdmin(admin), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Admin getByEmail(String email) {
        return crud.findByCorreoOrderByCorreoAsc(email).stream().map(admin -> {
            return mapper.toAdmin(admin);
        }).findAny().orElse(null);
    }

    @Override
    public boolean emailExist(String email) {
        return !crud.findByCorreoOrderByCorreoAsc(email).isEmpty();
    }


}
