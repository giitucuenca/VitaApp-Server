package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.ImageSubcategory;
import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.SubcategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.SubcategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import com.vitaapp.backend.tesis.persistence.mapper.SubcategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SubcategoriaRepository implements SubcategoryRepository {
    @Autowired
    private SubcategoriaCrudRepository crud;

    @Autowired
    private SubcategoryMapper mapper;

    @Autowired
    private ImagenSubcategoriaRepository imagen;

    @Override
    public List<Subcategory> getAll() {
        List<Subcategoria> subcategorias = crud.findByMostrarOrderByNombreAsc(true);

        List<Subcategory> subcategories = subcategorias.stream().map(subcategoria -> {
            Subcategory subcategory = mapper.toSubcategory(subcategoria);
            subcategory.setColor(subcategoria.getCategoria().getColor().getColor());
            return subcategory;
        }).collect(Collectors.toList());

        return subcategories;
    }

    @Override
    public List<Subcategory> getByCategory(int categoryId) {
        List<Subcategoria> subcategorias = crud.findByIdCategoriaAndMostrarOrderByNombreAsc(categoryId, true);
        List<Subcategory> subcategories = mapper.toSubcategories(subcategorias);
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
        Optional<Subcategoria> subcategoria =  crud.findById(id);
        if(subcategoria.isPresent()) {
            Subcategoria _subcategoria = subcategoria.get();
            Subcategory subcategory = mapper.toSubcategory(_subcategoria);
            subcategory.setColor(_subcategoria.getCategoria().getColor().getColor());
            return new ResponseEntity<>(subcategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> save(Subcategory subcategory) {
        subcategory.setShow(true);
        if(subcategory.getImages() != null && !subcategory.getImages().isEmpty()) {
            Subcategoria subcategoria = crud.save(mapper.toSubcategoria(subcategory));
            subcategory.getImages().forEach(images -> {
                images.setSubcategoryId(subcategoria.getIdSubcategoria());
                imagen.save(images);
            });

            ResponsePersonalized response = new ResponsePersonalized(200, "Subcategoria creada correctamente");
            response.setData(mapper.toSubcategory(crud.findById(subcategoria.getIdSubcategoria()).get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "Debe ingresar al menos 1 imagen");
            response.getErrors().add("Debe ingresar al menos 1 imagen");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponsePersonalized> deleteSubcategory(int id) {
        return crud.findById(id).map(subcategoria -> {
            subcategoria.setMostrar(false);
            crud.save(subcategoria);
            ResponsePersonalized response = new ResponsePersonalized(200, "Eliminada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe la subcategoria a eliminar");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<Subcategory> updateSubcategory(int id, Subcategory subcategory) {
        Optional<Subcategoria> subcategoriaData = crud.findById(id);
        Subcategoria subcategoria = mapper.toSubcategoria(subcategory);
        if (subcategoriaData.isPresent()) {
            Subcategoria _subcategoria = subcategoriaData.get();
            _subcategoria.setNombre(subcategoria.getNombre());
            _subcategoria.setDescripcion(subcategoria.getDescripcion());
            _subcategoria.setImagenUrl(subcategoria.getImagenUrl());
            _subcategoria.setIdCategoria(subcategoria.getIdCategoria());
            // imagen.delete(_subcategoria.getIdSubcategoria());
            List<ImageSubcategory> images = imagen.getImagesBySubcategoryId(id);
            images.forEach(image -> {
                boolean delete = true;
                for(ImageSubcategory imagePut : subcategory.getImages()) {
                    if(imagePut.getImageId() != null && imagePut.getImageId() == image.getImageId()) {
                        delete = false;
                        break;
                    }
                }
                if (delete) {
                    imagen.deleteById(image.getImageId());
                }
            });
            subcategory.getImages().forEach(image -> {
                image.setSubcategoryId(id);
                imagen.save(image);
            });
            mapper.toSubcategory(crud.save(_subcategoria));
            return getByIdSubcategory(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
