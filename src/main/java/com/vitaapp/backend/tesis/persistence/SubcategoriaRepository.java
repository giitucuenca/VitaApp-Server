package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import com.vitaapp.backend.tesis.persistence.mapper.SubcategoryMapper;
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

    @Override
    public List<Subcategory> getAll() {
        return subcategoryMapper.toSubcategories((List<Subcategoria>) subcategoriaCrudRepository.findAll());
    }

    @Override
    public List<Subcategory> getByCategory(int categoryId) {
        return subcategoryMapper.toSubcategories(subcategoriaCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId));
    }

    @Override
    public Optional<Subcategory> getByIdSubcategory(int id) {
        return subcategoriaCrudRepository.findById(id).map(subcategoria -> subcategoryMapper.toSubcategory(subcategoria));
    }

    @Override
    public ResponseEntity<Subcategory> save(Subcategory subcategory) {
        return new ResponseEntity<>(subcategoryMapper.toSubcategory(subcategoriaCrudRepository.save(subcategoryMapper.toSubcategoria(subcategory))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return getByIdSubcategory(id).map(subcategory -> {
            subcategoriaCrudRepository.deleteById(id);
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
            _subcategoria.setImagenURL(subcategoria.getImagenURL());
            _subcategoria.setIdCategoria(subcategoria.getIdCategoria());
            subcategoryMapper.toSubcategory(subcategoriaCrudRepository.save(_subcategoria));
            return new ResponseEntity<>(getByIdSubcategory(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
