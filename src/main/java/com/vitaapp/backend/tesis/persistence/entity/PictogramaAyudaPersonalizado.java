package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pictogramas_ayuda_personalizados")
public class PictogramaAyudaPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pictograma_personalizado")
    private Integer idPictogramaPersonalizado;

    @Column(length = 15)
    @Length(max = 15, message = "Longitud maxima del campo nombre max 15 caracteres")
    @NotBlank(message = "Debe ingresar un nombre de la categoria")
    private String nombre;

    @Column(name = "imagen_url")
    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    private Integer posicion;

    @Column(name = "id_pictograma")
    @NotNull(message = "Debe referenciar a algun pictograma")
    private Integer idPictograma;

    @Column(name = "id_ayuda")
    @NotNull(message = "Debe referenciar a alguna ayuda")
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
