package com.vitaapp.backend.tesis.domain.repository;

import org.springframework.http.ResponseEntity;

public interface ImageCategoryRepository {
    ResponseEntity<?> getImagesByCategoryId(int id);
}
