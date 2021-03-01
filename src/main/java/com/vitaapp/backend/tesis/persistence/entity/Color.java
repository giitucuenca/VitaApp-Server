package com.vitaapp.backend.tesis.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colores")
public class Color {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_color")
 private Integer idColor;
 private String color;
 private String nombre;
 @OneToMany(mappedBy = "color")
 private List<Categoria> categorias; 
 
 @OneToMany(mappedBy = "color")
 private List<CategoriaPersonalizada> categoriasPersonalizadas; 
 
public Integer getIdColor() {
	return idColor;
}
public void setIdColor(Integer idColor) {
	this.idColor = idColor;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public List<Categoria> getCategorias() {
	return categorias;
}
public void setCategorias(List<Categoria> categorias) {
	this.categorias = categorias;
}

    public List<CategoriaPersonalizada> getCategoriasPersonalizadas() {
        return categoriasPersonalizadas;
    }

    public void setCategoriasPersonalizadas(List<CategoriaPersonalizada> categoriasPersonalizadas) {
        this.categoriasPersonalizadas = categoriasPersonalizadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
