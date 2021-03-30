package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.AdminDetails;
import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.CarerDetails;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CarerService implements UserDetailsService {
    @Autowired
    CarerRepository carerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Carer carer = carerRepository.getByEmail(s);
        return new CarerDetails(carer);
    }

    public ResponseEntity<ResponsePersonalized> save(Carer carer) {
        carer.setPassword(passwordEncoder.encode(carer.getPassword()));
        return carerRepository.save(carer);
    }

    public Carer getByEmail(String email) {
        return this.carerRepository.getByEmail(email);
    }
}
