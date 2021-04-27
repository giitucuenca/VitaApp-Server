package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "ayudas")
public class Ayuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ayuda")
    private Integer idAyuda;

    @Length(max = 15, message = "Longitud maxima del campo nombre max 15 caracteres")
    @NotBlank(message = "Debe ingresar un nombre de la categoria")
    private String nombre;

    @Column(name = "imagen_url")
    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    @NotBlank(message = "Debe ingresar un color")
    private String color;

    @OneToMany(mappedBy = "ayuda")
    List<PictogramaAyudaPersonalizado> pictogramas;

    @OneToMany(mappedBy = "ayuda")
    List<CategoriaPersonalizada> categorias;


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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<CategoriaPersonalizada> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaPersonalizada> categorias) {
        this.categorias = categorias;
    }
}
