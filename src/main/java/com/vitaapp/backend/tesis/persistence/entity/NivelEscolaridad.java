package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "niveles_escolaridad")
public class NivelEscolaridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escolaridad")
    private Integer idEscolaridad;

    private String  escolaridad;

    @OneToMany(mappedBy = "escolaridad")
    private List<Adulto> adultos;

    public Integer getIdEscolaridad() {
        return idEscolaridad;
    }

    public void setIdEscolaridad(Integer idEscolaridad) {
        this.idEscolaridad = idEscolaridad;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public List<Adulto> getAdultos() {
        return adultos;
    }

    public void setAdultos(List<Adulto> adultos) {
        this.adultos = adultos;
    }
}
