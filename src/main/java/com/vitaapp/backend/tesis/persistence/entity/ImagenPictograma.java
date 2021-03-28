package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "imagenes_pictogramas")
public class ImagenPictograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    private String nombre;

    private String imagenUrl;

    @Column(name = "id_pictograma")
    private Integer idPictograma;

    @ManyToOne
    @JoinColumn(name = "id_pictograma", insertable = false, updatable = false)
    private Pictograma pictograma;

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

    public Pictograma getPictograma() {
        return pictograma;
    }

    public void setPictograma(Pictograma pictograma) {
        this.pictograma = pictograma;
    }
}
