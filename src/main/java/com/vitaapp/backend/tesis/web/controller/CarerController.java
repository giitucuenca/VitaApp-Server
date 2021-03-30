package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.AdminService;
import com.vitaapp.backend.tesis.domain.services.CarerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carer")
public class CarerController {
    @Autowired
    private CarerService carerService;

    @PostMapping("/register")
    public ResponseEntity<ResponsePersonalized> save(@RequestBody Carer carer) {
        return carerService.save(carer);
    }
}
