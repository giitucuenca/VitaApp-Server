package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAll(){
        return subcategoryRepository.getAll();
    }

    public List<Subcategory> getByCategory(int categoryId){
        return subcategoryRepository.getByCategory(categoryId);
    }

    public ResponseEntity<Subcategory> getByIdSubcategory(int id){
        return  subcategoryRepository.getByIdSubcategory(id);
    }

    public ResponseEntity<?> save(Subcategory subcategory){
        return subcategoryRepository.save(subcategory);
    }

    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id){
        return subcategoryRepository.deleteSubcategory(id);
    }

    public ResponseEntity<Subcategory> updateSubcategory(int id, Subcategory subcategory){
        return subcategoryRepository.updateSubcategory(id, subcategory);
    }
}
