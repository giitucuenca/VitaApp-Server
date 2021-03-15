package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.message.Response;
import org.springframework.http.ResponseEntity;

public interface AdminRepository {
    ResponseEntity<Response> save(Admin admin);
    ResponseEntity<Response> delete(Integer id);
    ResponseEntity<Admin> getByIdAdmin(Integer id);
    Admin getByEmail(String email);
}
