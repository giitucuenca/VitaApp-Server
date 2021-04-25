package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.SubcategoryCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubcategoriaPersonalizadaRepository implements SubcategoryCarerRepository {
    @Autowired
    private SubcategoriaPersonalizadaCrudRepository crud;

    @Autowired
    private SubcategoryCarerMapper mapper;

    @Autowired
    private PictogramaPersonalizadoRepository pictograma;

    @Override
    public List<SubcategoryCarer> getAll() {
        List<SubcategoriaPersonalizada> subcategorias = (List<SubcategoriaPersonalizada>) crud.findAll();
        List<SubcategoryCarer> subcategories = subcategorias.stream().map(subcategoria -> {
           SubcategoryCarer subcategory = mapper.toSubcategory(subcategoria);
           subcategory.setColor(subcategoria.getCategoriaPersonalizada().getColor());
           return subcategory;
        }).collect(Collectors.toList());
        return subcategories;
    }

    @Override
    public List<SubcategoryCarer> getByCategory(int categoryId) {
        List<SubcategoriaPersonalizada> subcategorias = (List<SubcategoriaPersonalizada>) crud.findByIdCategoriaPersonalizadaOrderByNombreAsc(categoryId);
        List<SubcategoryCarer> subcategories = subcategorias.stream().map(subcategoria -> {
            SubcategoryCarer subcategory = mapper.toSubcategory(subcategoria);
            subcategory.setColor(subcategoria.getCategoriaPersonalizada().getColor());
            return subcategory;
        }).collect(Collectors.toList());
        return subcategories;
    }

    @Override
    public ResponseEntity<?> getByIdSubcategory(int id) {
        Optional<SubcategoriaPersonalizada> subcategoria = crud.findById(id);
        if(subcategoria.isPresent()) {
            SubcategoryCarer subcategory = mapper.toSubcategory(subcategoria.get());
            subcategory.setColor(subcategoria.get().getCategoriaPersonalizada().getColor());
            return new ResponseEntity<>(subcategory, HttpStatus.OK);
        } else  {
            ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro la subcategoria");
            response.getErrors().add("No se encontro la subcategoria");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> save(SubcategoryCarer subcategory) {
        SubcategoryCarer subcategorySave = mapper.toSubcategory(crud.save(mapper.toSubcategoria(subcategory)));
        ResponsePersonalized response = new ResponsePersonalized(200, "Subcategoria creada correctamente");
        response.setData(subcategorySave);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return crud.findById(id).map(subcategoria -> {
            pictograma.deletePictogramsBySubcategoryId(id);
            crud.deleteById(id);
            return new ResponseEntity(new ResponsePersonalized(200, "Subcategoria eliminada"), HttpStatus.OK);
        }).orElse(new ResponseEntity(new ResponsePersonalized(404, "No se encontro el elemento a eliminar"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<?> updateSubcategory(int id, SubcategoryCarer subcategory) {
        Optional<SubcategoriaPersonalizada> subcategoria = crud.findById(id);
        if(subcategoria.isPresent()) {
            SubcategoriaPersonalizada _subcategoria = subcategoria.get();
            _subcategoria.setNombre(subcategory.getName());
            _subcategoria.setDescripcion(subcategory.getDescription());
            _subcategoria.setImagenUrl(subcategory.getImageUrl());
            _subcategoria.setIdCategoriaPersonalizada(subcategory.getCategoryId());
            ResponsePersonalized response = new ResponsePersonalized(200, "Subcategoria creada correctamente");
            response.setData(crud.save(_subcategoria));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe la subcategoria");
            response.getErrors().add("No existe la subcategoria");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            //return new ResponseEntity(new ResponsePersonalized(404, "No se encontro el elemento a eliminar"), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<?> saveList(List<SubcategoryCarer> subcategories) {
        List<SubcategoriaPersonalizada> subcategorias = (List<SubcategoriaPersonalizada>) crud.saveAll(mapper.toSubcategorias(subcategories));
        ResponsePersonalized response = new ResponsePersonalized(200, "Subcategorias creadas correctamente");
        response.setData(mapper.toSubcategories(subcategorias));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void deleteSubcategoriesByCategoryId(Integer categoryId) {
        List<SubcategoriaPersonalizada> categorias = (List<SubcategoriaPersonalizada>) crud.findByIdCategoriaPersonalizadaOrderByNombreAsc(categoryId);
        categorias.forEach(categoria -> {
            crud.deleteById(categoria.getIdSubcategoriaPersonalizada());
        });
    }
}
