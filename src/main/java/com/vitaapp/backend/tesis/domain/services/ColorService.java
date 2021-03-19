package com.vitaapp.backend.tesis.domain.services;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.ColorM;
import com.vitaapp.backend.tesis.domain.repository.ColorMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorMRepository colorMRepository;

    public List<ColorM> getAll() {
        return colorMRepository.getAll();
    }
}
