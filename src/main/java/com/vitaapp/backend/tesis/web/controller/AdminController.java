package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePersonalized> delete(@PathVariable Integer id) {
        return adminService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getByIdAdmin(@PathVariable Integer id) {
        return adminService.getByIdAdmin(id);
    }
}
