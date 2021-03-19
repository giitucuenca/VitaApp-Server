package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ColorM;
import com.vitaapp.backend.tesis.domain.repository.ColorMRepository;
import com.vitaapp.backend.tesis.persistence.crud.ColorCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Color;
import com.vitaapp.backend.tesis.persistence.mapper.ColorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColorRepository implements ColorMRepository {

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private ColorCrudRepository colorCrud;

    @Override
    public List<ColorM> getAll() {
        return colorMapper.colorsMTo ((List<Color>) colorCrud.findAll());
    }
}
