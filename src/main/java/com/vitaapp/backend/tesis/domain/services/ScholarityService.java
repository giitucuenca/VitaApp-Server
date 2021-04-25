package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Scholarity;
import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ScholarityRepository;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarityService {
    @Autowired
    private ScholarityRepository scholarity;

    public List<Scholarity> getAll() {
        return scholarity.getAll();
    }

}
