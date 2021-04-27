package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "pictogramas_ayuda")
public class PictogramaAyuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pictograma")
    private Integer idPictograma;

    @Column(length = 15)
    @Length(max = 15, message = "Longitud maxima del campo nombre max 15 caracteres")
    @NotBlank(message = "Debe ingresar el nombre del pictograma")
    private String nombre;

    @Column(name = "imagen_url")
    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    @NotBlank(message = "Debe ingresar un color")
    private String color;

    @OneToMany(mappedBy = "pictograma")
    List<PictogramaAyudaPersonalizado> pictogramasPersonalizados;

    @OneToMany(mappedBy = "pictograma")
    private List<ImagenPictogramaAyuda> imagenesPictogramas;

    public Integer getIdPictograma() {
        return idPictograma;
    }

    public void setIdPictograma(Integer idPictograma) {
        this.idPictograma = idPictograma;
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

    public List<PictogramaAyudaPersonalizado> getPictogramasPersonalizados() {
        return pictogramasPersonalizados;
    }

    public void setPictogramasPersonalizados(List<PictogramaAyudaPersonalizado> pictogramasPersonalizados) {
        this.pictogramasPersonalizados = pictogramasPersonalizados;
    }

    public List<ImagenPictogramaAyuda> getImagenesPictogramas() {
        return imagenesPictogramas;
    }

    public void setImagenesPictogramas(List<ImagenPictogramaAyuda> imagenesPictogramas) {
        this.imagenesPictogramas = imagenesPictogramas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
