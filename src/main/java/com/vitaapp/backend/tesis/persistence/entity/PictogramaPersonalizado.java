package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pictogramas_personalizados")
public class PictogramaPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pictograma_personalizado")
    private Integer idPictogramaPersonalizado;
    @Column(length = 15)
    @Length(max = 15, message = "Longitud maxima del campo nombre max 15 caracteres")
    @NotBlank(message = "Debe ingresar el nombre del pictograma")
    private String nombre;

    @Column(name = "imagen_url")
    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    private Integer posicion;

    private Boolean mostrar;

    @Column(name = "id_pictograma")
    @NotNull(message = "Debe ingresar el id del pictograma")
    private Integer idPictograma;

    @Column(name = "id_subcategoria_personalizada")
    @NotNull(message = "Debe ingresar el id de la subcategoria")
    private Integer idSubcategoriaPersonalizada;

    @ManyToOne
    @JoinColumn(name = "id_pictograma", insertable = false, updatable = false)
    private Pictograma pictograma;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria_personalizada", insertable = false, updatable = false)
    private SubcategoriaPersonalizada subcategoriaPersonalizada;

    public Integer getIdPictogramaPersonalizado() {
        return idPictogramaPersonalizado;
    }

    public void setIdPictogramaPersonalizado(Integer idPictogramaPersonalizado) {
        this.idPictogramaPersonalizado = idPictogramaPersonalizado;
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

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    public Integer getIdPictograma() {
        return idPictograma;
    }

    public void setIdPictograma(Integer idPictograma) {
        this.idPictograma = idPictograma;
    }

    public Pictograma getPictograma() {
        return pictograma;
    }

    public void setPictograma(Pictograma pictograma) {
        this.pictograma = pictograma;
    }

    public Integer getIdSubcategoriaPersonalizada() {
        return idSubcategoriaPersonalizada;
    }

    public void setIdSubcategoriaPersonalizada(Integer idSubcategoriaPersonalizada) {
        this.idSubcategoriaPersonalizada = idSubcategoriaPersonalizada;
    }

    public SubcategoriaPersonalizada getSubcategoriaPersonalizada() {
        return subcategoriaPersonalizada;
    }

    public void setSubcategoriaPersonalizada(SubcategoriaPersonalizada subcategoriaPersonalizada) {
        this.subcategoriaPersonalizada = subcategoriaPersonalizada;
    }
}
