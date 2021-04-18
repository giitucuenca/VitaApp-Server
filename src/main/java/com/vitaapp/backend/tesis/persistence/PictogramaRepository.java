package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.PictogramRepository;
import com.vitaapp.backend.tesis.persistence.crud.PictogramaCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
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

    @Autowired
    private ImagenPictogramaRepository imagen;

    @Override
    public List<Pictogram> getAll() {
        return  pictogramMapper.toPictograms((List<Pictograma>) pictogramaCrudRepository.findAll());
    }

    @Override
    public ResponseEntity<Pictogram> getById(int id) {
        Optional<Pictograma> pictograma = pictogramaCrudRepository.findById(id);
        if(pictograma.isPresent()) {
            Pictograma _pictograma = pictograma.get();
            Pictogram pictogram = pictogramMapper.toPictogram(_pictograma);
            pictogram.setColor(_pictograma.getSubcategoria().getCategoria().getColor().getColor());
            return new ResponseEntity<>(pictogram, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Pictogram> getAllByIdSubcategory(int id) {
        List<Pictograma> pictogramas = pictogramaCrudRepository.findByIdSubcategoriaOrderByNombreAsc(id);
        List<Pictogram> pictograms = pictogramMapper.toPictograms(pictogramas);
        if(!pictogramas.isEmpty()) {
            String color = pictogramas.get(0).getSubcategoria().getCategoria().getColor().getColor();
            pictograms.forEach(pictogram -> pictogram.setColor(color));
        }
        return pictograms;
    }

    @Override
    public ResponseEntity<Pictogram> save(Pictogram pictogram) {
        if(pictogram.getImages() != null && !pictogram.getImages().isEmpty()) {
            try {
               Pictogram _pictogram = pictogramMapper
                        .toPictogram(pictogramaCrudRepository.save(pictogramMapper.toPictograma(pictogram)));
               pictogram.getImages().forEach(image -> {
                   image.setPictogramId(_pictogram.getPictogramId());
                   imagen.save(image);
               });
               return new ResponseEntity<>(pictogramMapper.toPictogram(pictogramaCrudRepository.findById(_pictogram.getPictogramId()).get()) , HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(int id) {
        return pictogramaCrudRepository.findById(id).map(pictograma -> {
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
            _pictograma.setImagenUrl(pictogram.getImageUrl());
            imagen.delete(pictogram.getPictogramId());
            pictogram.getImages().forEach(image -> {
                imagen.save(image);
            });
            pictogramaCrudRepository.save(_pictograma);
            return getById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
