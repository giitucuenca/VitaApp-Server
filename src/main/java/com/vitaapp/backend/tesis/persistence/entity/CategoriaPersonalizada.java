package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "categorias_personalizadas")
public class CategoriaPersonalizada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_personalizada")
    private Integer idCategoriaPersonalizada;

    @Column(length = 15)
    @Length(max = 15, message = "Longitud maxima del campo nombre max 15 caracteres")
    @NotBlank(message = "Debe ingresar un nombre de la categoria")
    private String nombre;

    @Column(length = 40)
    @Length(max = 40, message = "Longitud maxima del campo descripción max 40 caracteres")
    @NotBlank(message = "Debe ingresar un nombre de la categoria")
    private String descripcion;

    @NotBlank(message = "Debe ingresar un color")
    private String color;

    @NotBlank(message = "Debe ingresar una imagen")
    private String imagenUrl;

    @Column(name = "id_cuidador")
    @NotNull(message = "Debe ingresar el id del cuidador")
    private Integer idCuidador;

    @Column(name = "id_categoria")
    @NotNull(message = "Debe ingresar el id de la categoria con la que se relaciona")
    private Integer idCategoria;

    @ManyToOne
    @JoinColumn(name = "id_cuidador", updatable = false, insertable = false)
    private Cuidador cuidador;

    @ManyToOne
    @JoinColumn(name = "id_categoria", updatable = false, insertable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "categoriaPersonalizada")
    List<SubcategoriaPersonalizada> subcategoriasPersonalizadas;


    public Integer getIdCategoriaPersonalizada() {
        return idCategoriaPersonalizada;
    }

    public void setIdCategoriaPersonalizada(Integer idCategoriaPersonalizada) {
        this.idCategoriaPersonalizada = idCategoriaPersonalizada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(Integer idCuidador) {
        this.idCuidador = idCuidador;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador cuidador) {
        this.cuidador = cuidador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public List<SubcategoriaPersonalizada> getSubcategoriasPersonalizadas() {
        return subcategoriasPersonalizadas;
    }

    public void setSubcategoriasPersonalizadas(List<SubcategoriaPersonalizada> subcategoriasPersonalizadas) {
        this.subcategoriasPersonalizadas = subcategoriasPersonalizadas;
    }
}
