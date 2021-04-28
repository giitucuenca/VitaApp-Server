package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Helper;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.HelperRepository;
import com.vitaapp.backend.tesis.persistence.crud.AyudaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Ayuda;
import com.vitaapp.backend.tesis.persistence.mapper.CategoryCarerMapper;
import com.vitaapp.backend.tesis.persistence.mapper.HelperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AyudaRepository implements HelperRepository {
    @Autowired
    private AyudaCrudRepository crud;
    @Autowired
    private HelperMapper mapper;
    @Autowired
    private CategoriaPersonalizadaRepository categoriaRepository;
    @Autowired
    private CategoryCarerMapper categoryMapper;

    @Autowired
    private PictogramaAyudaPersonalizadoRepository pictogramaAyuda;

    @Override
    public ResponseEntity<?> save(Helper helper) {
        Ayuda ayuda = crud.save(mapper.toAyuda(helper));
        ResponsePersonalized response = new ResponsePersonalized(200, "Ayuda creada correctamente");
        response.setData(mapper.toHelper(ayuda));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Helper> helpers =  mapper.toHelpers((List<Ayuda>) crud.findAllByOrderByNombreAsc());
        return new ResponseEntity<>(helpers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Integer id) {
        ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro la ayuda");
        response.getErrors().add("No se encontro la ayuda");
        Optional<Ayuda> ayuda = crud.findById(id);
        if(ayuda.isPresent()) {
            return new ResponseEntity<>(mapper.toHelper(ayuda.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(Integer id, Helper helper) {
        Optional<Ayuda> _ayuda = crud.findById(id);
        if(_ayuda.isPresent()) {
            Ayuda ayuda = _ayuda.get();
            ayuda.setNombre(helper.getName());
            ayuda.setImagenUrl(helper.getImageUrl());
            ayuda.setColor(helper.getColor());
            Ayuda ayudaSave =  crud.save(ayuda);
            ResponsePersonalized response = new ResponsePersonalized(200, "La ayuda se modifico correctamente");
            response.setData(mapper.toHelper(ayudaSave));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro la ayuda");
            response.getErrors().add("No se encontro la ayuda");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public ResponseEntity<?> delete(Integer id) {
        Optional<Ayuda> _ayuda = crud.findById(id);
        if (_ayuda.isPresent()) {
            Ayuda ayuda = _ayuda.get();
            ayuda.getCategorias().forEach(categoria -> {
                categoria.setIdAyuda(null);
                categoriaRepository.updateCategory(categoria.getIdCategoria(), categoryMapper.toCategory(categoria));
            });
            ayuda.getPictogramas().forEach(pictograma -> {
                pictogramaAyuda.delete(pictograma.getIdPictogramaPersonalizado());
            });
            crud.deleteById(id);
            ResponsePersonalized response = new ResponsePersonalized(200, "Ayuda eliminada");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ResponsePersonalized response = new ResponsePersonalized(404, "No se encontro la ayuda");
            response.getErrors().add("No se encontro la ayuda");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
