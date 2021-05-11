package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ayudas")
public class Ayuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ayuda")
    private Integer idAyuda;

    @Length(max = 30, message = "Longitud maxima del campo nombre max 30 caracteres")
    @NotBlank(message = "Debe ingresar un nombre de la ayuda")
    private String nombre;

    @Column(name = "imagen_url")
    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    @NotBlank(message = "Debe ingresar un color")
    private String color;

    @Column(name = "id_cuidador")
    @NotNull(message = "Debe ingresar el id del cuidador que creo la ayuda")
    private Integer idCuidador;

    @ManyToOne
    @JoinColumn(name = "id_cuidador", insertable = false, updatable = false)
    private Cuidador cuidador;

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

    public Integer getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Integer idCuidador) {
        this.idCuidador = idCuidador;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }
}
