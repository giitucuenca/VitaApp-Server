package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "imagenes_subcategorias")
public class ImagenSubcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @NotBlank(message = "Debe ingresar un nombre de la imagen")
    private String nombre;

    @NotBlank(message = "Debe ingresar un URL de la imagen")
    private String imagenUrl;

    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", insertable = false, updatable = false)
    private Subcategoria subcategoria;

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

    public Integer getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(Integer idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
}
