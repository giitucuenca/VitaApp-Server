package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subcategorias_personalizadas_pictogramas")
public class SubcategoriaPersonalizadaPictograma {
	@EmbeddedId
	private SubcategoriaPersonalizadaPictogramaPK id;
	private Integer posicion;
	
	
	public SubcategoriaPersonalizadaPictogramaPK getId() {
		return id;
	}
	public void setId(SubcategoriaPersonalizadaPictogramaPK id) {
		this.id = id;
	}
	public Integer getPosicion() {
		return posicion;
	}
	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	} 
}

