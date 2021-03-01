package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subcategorias_personalizadas")
public class SubcategoriaPersonalizada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subcategoria_personalizada")
	private Integer idSubcategoriaPersonalizada;
	private String nombre;
	private String descripcion;
	public Integer getIdSubcategoriaPersonalizada() {
		return idSubcategoriaPersonalizada;
	}
	public void setIdSubcategoriaPersonalizada(Integer idSubcategoriaPersonalizada) {
		this.idSubcategoriaPersonalizada = idSubcategoriaPersonalizada;
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
	
}
