package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias_personalizadas")
public class CategoriaPersonalizada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria_personalizada")
	private Integer idCategoriaPersonalizada;
	private String nombre;
	private String descripcion;
	private String color;
	@Column(name = "id_cuidador")
	private Integer idCuidador;
	@Column(name = "id_categoria")
	private Integer idCategoria;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	
}
