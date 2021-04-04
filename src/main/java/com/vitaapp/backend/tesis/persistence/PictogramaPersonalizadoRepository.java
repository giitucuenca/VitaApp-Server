package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramCarerRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaPersonalizadaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramCarerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PictogramaPersonalizadoRepository implements PictogramCarerRepository {
    @Autowired
    private PictogramaPersonalizadaCrudRepository pictogramaCrud;

    @Autowired
    private PictogramCarerMapper pictogramMapper;


    @Override
    public List<PictogramCarer> getAll() {
        return pictogramMapper.toPictograms((List< PictogramaPersonalizado>)pictogramaCrud.findAll());
    }

    @Override
    public Optional<PictogramCarer> getById(int id) {
        return pictogramaCrud.findById(id)
                .map(pictograma -> pictogramMapper.toPictogram(pictograma));
    }

    @Override
    public List<PictogramCarer> getAllByIdSubcategory(int id) {
        return pictogramMapper.toPictograms(pictogramaCrud.findByIdSubcategoriaPersonalizadaOrderByNombreAsc(id));
    }

    @Override
    public ResponseEntity<PictogramCarer> save(PictogramCarer pictogram) {
        return new ResponseEntity<>(pictogramMapper.toPictogram(pictogramaCrud.save(pictogramMapper.toPictograma(pictogram))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return pictogramaCrud.findById(id).map(pictograma -> {
            pictogramaCrud.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Pictograma eliminado correctamente."), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(new ResponsePersonalized(404, "No se encontro el pictograma a eliminar"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<PictogramCarer> update(int id, PictogramCarer pictogram) {
        Optional<PictogramaPersonalizado> pictograma = pictogramaCrud.findById(id);
        if(pictograma.isPresent()) {
            PictogramaPersonalizado _pictograma = pictograma.get();
            _pictograma.setNombre(pictogram.getName());
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            _pictograma.setIdSubcategoriaPersonalizada(pictogram.getSubcategoryId());
            _pictograma.setPosicion(pictogram.getPosition());
            return new ResponseEntity<>(pictogramMapper.toPictogram(pictogramaCrud.save(_pictograma)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
