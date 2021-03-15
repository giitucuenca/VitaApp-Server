package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.message.Response;
import com.vitaapp.backend.tesis.domain.repository.CategoryRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {
    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;
    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<Category> getAll() {
        // TODO Auto-generated method stub
        return mapper.toCategories((List<Categoria>) categoriaCrudRepository.findAll());
    }

    @Override
    public Optional<Category> getByIdCategory(int id) {
        return categoriaCrudRepository.findById(id).map(categoria -> mapper.toCategory(categoria));
    }

    @Override
    public ResponseEntity<String> save(Category category) {
        // TODO Auto-generated method stub
        try {
            mapper.toCategory(categoriaCrudRepository.save(mapper.toCategoria(category)));
            return new ResponseEntity<>("Categoria Agregada Correctamente", HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response> delete(Integer id) {
        return getByIdCategory(id).map(category -> {
            categoriaCrudRepository.deleteById(id);
            Response response = new Response();
            response.setCode(200);
            response.setMessage("Categoria Eliminada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> {
			Response response = new Response();
			response.setCode(404);
			response.setMessage("Categoria no encontrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<Response> updateCategory(Integer id, Category category) {
        Optional<Categoria> categoriaData = categoriaCrudRepository.findById(id);
        Categoria categoria = mapper.toCategoria(category);
        if (categoriaData.isPresent()) {
            Categoria _categoria = categoriaData.get();
            _categoria.setNombre(categoria.getNombre());
            _categoria.setDescripcion(categoria.getDescripcion());
            _categoria.setImagenURL(categoria.getImagenURL());
            _categoria.setIdColor(categoria.getIdColor());
            mapper.toCategory(categoriaCrudRepository.save(_categoria));
            Response response = new Response();
            response.setCode(200);
            response.setMessage("Categoria Modificada Correctamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
