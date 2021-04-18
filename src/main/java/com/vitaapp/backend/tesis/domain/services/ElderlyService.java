package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ElderlyRepository elderly;

    public ResponseEntity<ResponsePersonalized> save(Elderly older) {
        older.setPassword(passwordEncoder.encode(older.getPassword()));
        return elderly.save(older);
    }

    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return elderly.delete(id);
    }

    public ResponseEntity<List<Elderly>> getByIdCarer(Integer id) {
        return elderly.getByIdCarer(id);
    }

    public Elderly getByEmail(String email) {
        return getByEmail(email);
    }
}
