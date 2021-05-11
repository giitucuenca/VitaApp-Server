package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.CategoryCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.CuidadorCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaPersonalizadaRepository implements CategoryCarerRepository {

    @Autowired
    private CategoriaPersonalizadaCrudRepository crud;

    @Autowired
    private CuidadorCrudRepository cuidadorCrud;

    @Autowired
    private CategoryCarerMapper mapper;

    @Autowired
    private SubcategoriaPersonalizadaRepository subcategoria;

    @Autowired
    private PictogramaPersonalizadoRepository pictograma;

    @Autowired
    private AdultoCategoriaRepository adultoCategoriaRepository;


    @Override
    public List<CategoryCarer> getAll() {
        return mapper.toCategories((List<CategoriaPersonalizada>) crud.findAll());
    }

    @Override
    public ResponseEntity<?> getCategoryByCarerId(int carerId) {
        Optional<Cuidador> cuidador = cuidadorCrud.findById(carerId);
        if (cuidador.isPresent()) {
            return new ResponseEntity<>(mapper.toCategories(crud.findByIdCuidadorOrderByNombreAsc(carerId)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<CategoryCarer> getByIdCategory(int id) {
        return crud
                .findById(id)
                .map(categoriaPersonalizada -> new ResponseEntity<>(mapper.toCategory(categoriaPersonalizada), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponsePersonalized> save(CategoryCarer category) {
            crud.save(mapper.toCategoria(category));
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria creada"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        if(crud.findById(id).isPresent()) {
            adultoCategoriaRepository.deleteByIdCategoria(id);
            pictograma.deletePictogramsByCategoryId(id);
            subcategoria.deleteSubcategoriesByCategoryId(id);
            crud.deleteById(id);
            ResponsePersonalized response = new ResponsePersonalized(200, "Categoria Eliminada Correctamente");
            response.setMessage("Categoria Eliminada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No existe la categoria");
            response.setMessage("No existe la categoria");
            response.getErrors().add("No existe la categoria");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        /* return crud.findById(id).map(categoria -> {
            crud.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Eliminada"), HttpStatus.OK);
        }).orElseGet(() -> {
            return new ResponseEntity<>(new ResponsePersonalized(404, "Categoria no encontrada"), HttpStatus.NOT_FOUND);
        }); */
    }

    @Override
    public ResponseEntity<ResponsePersonalized> updateCategory(Integer id, CategoryCarer category) {
        Optional<CategoriaPersonalizada> categoriaData = crud.findById(id);
        CategoriaPersonalizada categoria = mapper.toCategoria(category);
        if (categoriaData.isPresent()) {
            CategoriaPersonalizada _categoria = categoriaData.get();
            _categoria.setNombre(categoria.getNombre());
            _categoria.setDescripcion(categoria.getDescripcion());
            _categoria.setImagenUrl(categoria.getImagenUrl());
            _categoria.setColor(categoria.getColor());
            _categoria.setIdAyuda(categoria.getIdAyuda());
            crud.save(_categoria);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Categoria Modificada Correctamente"), HttpStatus.OK);
        } else {
            throw new RuntimeException("No se encontro el elmento con id: " + id + " a modificar");
        }
    }

    @Override
    public ResponseEntity<?> saveList(List<CategoryCarer> categories) {
        List<CategoriaPersonalizada> categorias = (List<CategoriaPersonalizada>) crud.saveAll(mapper.toCategorias(categories));
        ResponsePersonalized response = new ResponsePersonalized(200, "Categorias creadas correctamente");
        response.setData(mapper.toCategories(categorias));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
