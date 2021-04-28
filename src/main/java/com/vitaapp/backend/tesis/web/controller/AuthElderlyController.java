package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.dto.AuthenticationRequest;
import com.vitaapp.backend.tesis.domain.dto.AuthenticationResponse;
import com.vitaapp.backend.tesis.domain.services.ElderlyService;
import com.vitaapp.backend.tesis.domain.services.VitaappUserDetailsService;
import com.vitaapp.backend.tesis.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elderly")
public class AuthElderlyController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ElderlyService carerService;

    @Autowired
    private VitaappUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {

        Elderly elderly = carerService.getByUsername(request.getUsername());
        String userName = request.getUsername();
        if(elderly == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            userName = "older-" + userName;
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, request.getPassword()));
            UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException exception) {
            return  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
