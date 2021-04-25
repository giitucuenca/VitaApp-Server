package com.vitaapp.backend.tesis.domain.repository;

import org.springframework.http.ResponseEntity;

public interface ImageRepository {
    ResponseEntity<?> getImageByFatherId(int id);
}

