package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ayudas")
public class Ayuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idAyuda;
    private String nombre;
    private String imagenUrl;

    @OneToMany(mappedBy = "ayuda")
    List<PictogramaAyudaPersonalizado> pictogramas;

    public Integer getIdAyuda() {
        return idAyuda;
    }

    public void setIdAyuda(Integer idAyuda) {
        this.idAyuda = idAyuda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public List<PictogramaAyudaPersonalizado> getPictogramas() {
        return pictogramas;
    }

    public void setPictogramas(List<PictogramaAyudaPersonalizado> pictogramas) {
        this.pictogramas = pictogramas;
    }


}
