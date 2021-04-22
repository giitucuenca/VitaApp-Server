package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
@Entity
@Table(name="pictogramas_ayuda_personalizados")
public class PictogramaAyudaPersonalizado {
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

    @Column(name = "id_ayuda")
    private Integer idAyuda;

    @ManyToOne
    @JoinColumn(name = "id_pictograma", insertable = false, updatable = false)
    private PictogramaAyuda pictograma;

    @ManyToOne
    @JoinColumn(name = "id_ayuda", insertable = false, updatable = false)
    private Ayuda ayuda;

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

    public PictogramaAyuda getPictograma() {
        return pictograma;
    }

    public void setPictograma(PictogramaAyuda pictograma) {
        this.pictograma = pictograma;
    }

    public Integer getIdAyuda() {
        return idAyuda;
    }

    public void setIdAyuda(Integer idAyuda) {
        this.idAyuda = idAyuda;
    }

    public Ayuda getAyuda() {
        return ayuda;
    }

    public void setAyuda(Ayuda ayuda) {
        this.ayuda = ayuda;
    }
}
