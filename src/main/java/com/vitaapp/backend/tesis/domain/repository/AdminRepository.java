package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import org.springframework.http.ResponseEntity;

public interface AdminRepository {
    ResponseEntity<ResponsePersonalized> save(Admin admin);
    ResponseEntity<ResponsePersonalized> delete(Integer id);
    ResponseEntity<Admin> getByIdAdmin(Integer id);
    Admin getByEmail(String email);
}
