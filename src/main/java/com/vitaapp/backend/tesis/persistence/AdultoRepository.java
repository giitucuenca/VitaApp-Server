package com.vitaapp.backend.tesis.persistence;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.domain.message.ResponsePersonalized;
import com.vitaapp.backend.tesis.domain.repository.ElderlyRepository;
import com.vitaapp.backend.tesis.persistence.crud.AdultoCrudRepository;
import com.vitaapp.backend.tesis.persistence.entity.Adulto;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizadaPK;
import com.vitaapp.backend.tesis.persistence.mapper.ElderlyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdultoRepository implements ElderlyRepository {

    @Autowired
    private ElderlyMapper elderlyMapper;

    @Autowired
    private AdultoCrudRepository adultoCrud;

    @Autowired
    private AdultoCategoriaRepository adultoCategoriaRepository;

    @Override
    public ResponseEntity<ResponsePersonalized> save(Elderly older) {
        if(!adultoCrud.findByUsername(older.getUsername()).isEmpty()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        elderlyMapper.toElderly(adultoCrud.save(elderlyMapper.toAdulto(older)));
        return new ResponseEntity<>(new ResponsePersonalized(200, "Adulto creado correctamente.") , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePersonalized> delete(Integer id) {
        return adultoCrud.findById(id).map(adulto-> {
            adultoCategoriaRepository.deleteByIdAdulto(id);
            adultoCrud.deleteById(id);
            return new ResponseEntity<>(new ResponsePersonalized(200, "Adulto Eliminado Correctamente"), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Elderly>> getByIdCarer(Integer id) {
        return new ResponseEntity<>(elderlyMapper.toElderlies(adultoCrud.findByIdCuidadorOrderByNombre(id)), HttpStatus.OK);
    }

    @Override
    public Elderly getByUsername(String userName) {
        return adultoCrud.findByUsername(userName).stream().map(adulto -> elderlyMapper.toElderly(adulto)).findAny().orElse(null);
    }

    @Override
    public ResponseEntity<?> update(Integer id, Elderly elderly) {
        Optional<Adulto> _adulto = adultoCrud.findById(id);
        if(_adulto.isPresent()) {
            Adulto adulto = _adulto.get();
            adulto.setNombre(elderly.getName());
            adulto.setApellido(elderly.getSurname());
            adulto.setGenero(elderly.getGender());
            adulto.setLateralidad(elderly.getLaterality());
            adulto.setIdEscolaridad(elderly.getScholarityId());
            ResponsePersonalized response = new ResponsePersonalized(200, "Adulto modificado correctamente");
            response.setData(elderlyMapper.toElderly(adultoCrud.save(adulto)));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new RuntimeException("No existe el adulto mayor");
        }
    }
}
