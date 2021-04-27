package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/any/category/{id}")
    public ResponseEntity<?> getImagesByCategoryId(@PathVariable int id) {
        return imageService.getImagesByCategoryId(id);
    }

    @GetMapping("/any/subcategory/{id}")
    public ResponseEntity<?> getImagesBySubcategoryId(@PathVariable int id) {
        return imageService.getImagesBySubcategoryId(id);
    }

    @GetMapping("/any/pictogram/{id}")
    public ResponseEntity<?> getImagesByPictogramId(@PathVariable int id) {
        return imageService.getImagesByPictogramId(id);
    }

    @GetMapping("/any/pictogram-help/{id}")
    public ResponseEntity<?> getImagesByPictogramHelpId(@PathVariable int id) {
        return imageService.getImagesByPictogramHelpId(id);
    }

    @GetMapping("/any/subcategory/category/{id}")
    public ResponseEntity<?> getImagesSubcategoryByCategoryId(@PathVariable int id) {
        return imageService.getImagesSubcategoryByCategoryId(id);
    }
}
