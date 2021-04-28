package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import com.vitaapp.backend.tesis.domain.repository.ElderlyCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyCategoryService {
    @Autowired
    private ElderlyCategoryRepository repository;

    public ResponseEntity<?> getElderlyCategoryByElderlyId(Integer elderlyId) {
        return repository.getElderlyCategoryByElderlyId(elderlyId);
    }

    public ResponseEntity<?> updateList(Integer elderlyId, List<ElderlyCategory> elderlyCategoryList) {
        return repository.updateList(elderlyId, elderlyCategoryList);
    }


}
