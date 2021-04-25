package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.AdminDetails;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> save(Admin admin) {
        if(admin.getPassword().length() < 6) {
            ResponsePersonalized response = new ResponsePersonalized(404, "La Contraseña debe tener minimo 6 caracteres");
            response.getErrors().add("La Contraseña debe tener minimo 6 caracteres");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return adminRepository.delete(id);
    }

    public ResponseEntity<Admin> getByIdAdmin(Integer id) {

        return adminRepository.getByIdAdmin(id);
    }



    public Admin getByEmail(String email) {
        return this.adminRepository.getByEmail(email);
    }



}
