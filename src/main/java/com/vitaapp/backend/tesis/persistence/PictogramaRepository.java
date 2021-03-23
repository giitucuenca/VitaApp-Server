package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramRepository;
import com.vitaapp.backend.tesis.persistence.crud.CategoriaCrudRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import com.vitaapp.backend.tesis.persistence.mapper.PictogramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PictogramaRepository implements PictogramRepository {
    @Autowired
    private PictogramaCrudRepository pictogramaCrudRepository;

    @Autowired
    private PictogramMapper pictogramMapper;

    @Override
    public List<Pictogram> getAll() {
        return  pictogramMapper.toPictograms((List<Pictograma>) pictogramaCrudRepository.findAll());
    }

    @Override
    public Optional<Pictogram> getById(int id) {
        return pictogramaCrudRepository
                .findById(id)
                .map(pictograma -> pictogramMapper.toPictogram(pictograma));
    }

    @Override
    public List<Pictogram> getAllByIdSubcategory(int id) {
        return pictogramMapper.toPictograms((List<Pictograma>) pictogramaCrudRepository.findByIdSubcategoriaOrderByNombreAsc(id));
    }

    @Override
    public ResponseEntity<Pictogram> save(Pictogram pictogram) {
        try {
           Pictogram _pictogram = pictogramMapper
                    .toPictogram(pictogramaCrudRepository.save(pictogramMapper.toPictograma(pictogram)));
           return new ResponseEntity<>(_pictogram, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return getById(id).map(pictograma -> {
            pictogramaCrudRepository.deleteById(id);
            ResponsePersonalized response = new ResponsePersonalized(200, "Pictograma Eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }).orElseGet(() -> {
            ResponsePersonalized response = new ResponsePersonalized(200, "Pictograma no encontrado");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public ResponseEntity<Pictogram> update(int id, Pictogram pictogram) {
        Optional<Pictograma> pictogramaData = pictogramaCrudRepository.findById(id);
        Pictograma pictograma = pictogramMapper.toPictograma(pictogram);
        if(pictogramaData.isPresent()) {
            Pictograma _pictograma = pictogramaData.get();
            _pictograma.setNombre(pictograma.getNombre());
            _pictograma.setIdSubcategoria(pictograma.getIdSubcategoria());
            _pictograma.setImagenURL(pictogram.getImageURL());
            pictogramaCrudRepository.save(_pictograma);
            return new ResponseEntity<>(getById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
