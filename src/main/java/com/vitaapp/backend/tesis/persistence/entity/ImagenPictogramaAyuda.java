package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "imagenes_pictrogramas_ayuda")
public class ImagenPictogramaAyuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @NotBlank(message = "Debe ingresar un nombre de la imagen")
    private String nombre;

    @NotBlank(message = "Debe ingresar un URL de la imagen")
    private String imagenUrl;

    @Column(name = "id_pictograma")
    private Integer idPictograma;

    @ManyToOne
    @JoinColumn(name = "id_pictograma", insertable = false, updatable = false)
    private PictogramaAyuda pictograma;

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
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
}

