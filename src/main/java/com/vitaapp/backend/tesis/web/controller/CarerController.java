package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.dto.AuthenticationResponse;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.services.CarerService;
import com.vitaapp.backend.tesis.domain.services.VitaappUserDetailsService;
import com.vitaapp.backend.tesis.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carer")
public class CarerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CarerService carerService;

    @Autowired
    private VitaappUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody Carer carer) {
        String password = carer.getPassword();
        if(password.length() < 6) {
            ResponsePersonalized response = new ResponsePersonalized(400, "Error en la consulta");
            response.getErrors().add("La contraseña debe tener minimo 6 caracteres");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<?> response = carerService.save(carer);
        if(response.getBody() instanceof  Carer) {
            Carer _carer = (Carer) response.getBody();
            String userName = carer.getEmail();
            try {
                userName = "carer-" + userName;
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
                UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
                String jwt = jwtUtil.generateToken(userDetails);
                _carer.setToken(jwt);
                return new ResponseEntity<>(_carer, HttpStatus.OK);
            } catch (BadCredentialsException exception) {
                return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return response;
        }

    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(name="Authorization") String token) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token).substring(6);
        return new ResponseEntity<>(this.carerService.getByEmail(email), HttpStatus.OK);
    }
}
