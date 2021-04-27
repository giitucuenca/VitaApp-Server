package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.repository.ImageCategoryRepository;
import com.vitaapp.backend.tesis.domain.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    @Qualifier("category")
    private ImageRepository imageCat;

    @Autowired
    @Qualifier("subcategory")
    private ImageRepository imageSub;

    @Autowired
    @Qualifier("pictogram")
    private ImageRepository imagePic;

    @Autowired
    @Qualifier("pictogramHelp")
    private ImageRepository imagePicHelp;

    @Autowired
    private ImageCategoryRepository imageCategorySub;


    public ResponseEntity<?> getImagesByCategoryId(int id) {
        return imageCat.getImageByFatherId(id);
    }

    public ResponseEntity<?> getImagesBySubcategoryId(int id) {
        return imageSub.getImageByFatherId(id);
    }

    public ResponseEntity<?> getImagesByPictogramId(int id) {
        return imagePic.getImageByFatherId(id);
    }

    public ResponseEntity<?> getImagesByPictogramHelpId(int id) {
        return imagePicHelp.getImageByFatherId(id);
    }

    public ResponseEntity<?> getImagesSubcategoryByCategoryId(int id) {
        return imageCategorySub.getImagesByCategoryId(id);
    }
}
