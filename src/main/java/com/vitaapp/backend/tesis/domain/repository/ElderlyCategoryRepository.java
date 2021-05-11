package com.vitaapp.backend.tesis.domain.repository;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ElderlyCategoryRepository {
    public ResponseEntity<?> saveList(List<ElderlyCategory> elderlyCategoryList);
    public ResponseEntity<?> getElderlyCategoryByElderlyId(Integer elderlyId);
    public ResponseEntity<?> updateList(Integer elderlyId, List<ElderlyCategory> elderlyCategoryList);
    public ResponseEntity<?> getCategoriesByElderlyId(Integer elderlyId);
}
