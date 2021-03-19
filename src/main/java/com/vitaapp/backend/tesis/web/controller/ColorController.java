package com.vitaapp.backend.tesis.web.controller;

import com.vitaapp.backend.tesis.domain.ColorM;
import com.vitaapp.backend.tesis.domain.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/all")
    public List<ColorM> getAll() {
        return colorService.getAll();
    }
}
