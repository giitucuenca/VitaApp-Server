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
    AdminMapper adminMapper;

    @Autowired
    AdministradorCrudRepository administradorCrudRepository;

    @Override
    public ResponseEntity<ResponsePersonalized> save(Admin admin) {
        System.out.println(admin.getEmail());
        administradorCrudRepository.findByCorreoOrderByCorreoAsc(admin.getEmail()).isEmpty();
        if(administradorCrudRepository.findByCorreoOrderByCorreoAsc(admin.getEmail()).isEmpty()) {
            ResponsePersonalized response = new ResponsePersonalized();
            response.setMessage("Administrador creado correctamente.");
            response.setCode(200);
            administradorCrudRepository.save(adminMapper.toAdministrador(admin));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            ResponsePersonalized response = new ResponsePersonalized();
            response.setMessage("El email ya existe.");
            response.setCode(404);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return administradorCrudRepository.findById(id)
                .map(admin -> {
                   administradorCrudRepository.deleteById(id);
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
        return administradorCrudRepository.findById(id)
                .map(admin -> new ResponseEntity<>(adminMapper.toAdmin(admin), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Admin getByEmail(String email) {
        return administradorCrudRepository.findByCorreoOrderByCorreoAsc(email).stream().map(admin -> {
            return adminMapper.toAdmin(admin);
        }).findAny().orElse(null);
    }
}
