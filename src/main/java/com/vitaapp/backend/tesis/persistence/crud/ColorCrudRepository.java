package com.vitaapp.backend.tesis.persistence.crud;

import com.vitaapp.backend.tesis.persistence.entity.Color;
import org.springframework.data.repository.CrudRepository;

public interface ColorCrudRepository extends CrudRepository<Color, Integer> {
}
