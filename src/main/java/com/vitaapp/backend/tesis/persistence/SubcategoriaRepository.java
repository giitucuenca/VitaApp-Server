package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import com.vitaapp.backend.tesis.persistence.mapper.SubcategoryMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SubcategoriaRepository implements SubcategoryRepository {
    @Autowired
    private SubcategoriaCrudRepository subcategoriaCrudRepository;

    @Autowired
    private SubcategoryMapper subcategoryMapper;

    @Autowired
    private ImagenSubcategoriaRepository imagen;

    @Override
    public List<Subcategory> getAll() {
        List<Subcategoria> subcategorias = subcategoriaCrudRepository.findByMostrarOrderByNombreAsc(true);
        List<Subcategory> subcategories = subcategoryMapper.toSubcategories(subcategorias);
        return subcategories;
    }

    @Override
    public List<Subcategory> getByCategory(int categoryId) {
        List<Subcategoria> subcategorias = subcategoriaCrudRepository.findByIdCategoriaAndMostrarOrderByNombreAsc(categoryId, true);
        List<Subcategory> subcategories = subcategoryMapper.toSubcategories(subcategorias);
        if(subcategorias.size() > 0) {
            String color = subcategorias.get(0).getCategoria().getColor().getColor();
            subcategories.forEach(subcategory -> {
                subcategory.setColor(color);
            });
        }
        return subcategories;
    }

    @Override
    public ResponseEntity<Subcategory> getByIdSubcategory(int id) {
        Optional<Subcategoria> subcategoria =  subcategoriaCrudRepository.findById(id);
        if(subcategoria.isPresent()) {
            Subcategoria _subcategoria = subcategoria.get();
            Subcategory subcategory = subcategoryMapper.toSubcategory(_subcategoria);
            subcategory.setColor(_subcategoria.getCategoria().getColor().getColor());
            return new ResponseEntity<>(subcategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Subcategory> save(Subcategory subcategory) {
        subcategory.setShow(true);
        if(subcategory.getImages() != null && !subcategory.getImages().isEmpty()) {
        try {
            Subcategoria subcategoria = subcategoriaCrudRepository.save(subcategoryMapper.toSubcategoria(subcategory));
            subcategory.getImages().forEach(images -> {
                images.setSubcategoryId(subcategoria.getIdSubcategoria());
                imagen.save(images);
            });
            return new ResponseEntity<>(subcategoryMapper.toSubcategory(subcategoriaCrudRepository.findById(subcategoria.getIdSubcategoria()).get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return subcategoriaCrudRepository.findById(id).map(subcategoria -> {
            subcategoria.setMostrar(false);
            subcategoriaCrudRepository.save(subcategoria);
            ResponsePersonalized response = new ResponsePersonalized(200, "Eliminada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe la subcategoria a eliminar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<Subcategory> updateSubcategory(int id, Subcategory subcategory) {
        Optional<Subcategoria> subcategoriaData = subcategoriaCrudRepository.findById(id);
        Subcategoria subcategoria = subcategoryMapper.toSubcategoria(subcategory);
        if (subcategoriaData.isPresent()) {
            Subcategoria _subcategoria = subcategoriaData.get();
            _subcategoria.setNombre(subcategoria.getNombre());
            _subcategoria.setDescripcion(subcategoria.getDescripcion());
            _subcategoria.setImagenUrl(subcategoria.getImagenUrl());
            _subcategoria.setIdCategoria(subcategoria.getIdCategoria());
            imagen.delete(_subcategoria.getIdSubcategoria());
            subcategory.getImages().forEach(images -> {
                imagen.save(images);
            });
            subcategoryMapper.toSubcategory(subcategoriaCrudRepository.save(_subcategoria));
            return getByIdSubcategory(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
