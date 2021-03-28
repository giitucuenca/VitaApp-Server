package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictogramas_personalizados")
public class PictogramaPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pictograma_personalizado")
    private Integer idPictogramaPersonalizado;

    private String nombre;

    @Column(name = "imagen_url")
    private String imagenUrl;

    private Integer posicion;

    private Boolean mostrar;

    @Column(name = "id_pictograma")
    private Integer idPictograma;

    @Column(name = "id_subcategoria_personalizada")
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
