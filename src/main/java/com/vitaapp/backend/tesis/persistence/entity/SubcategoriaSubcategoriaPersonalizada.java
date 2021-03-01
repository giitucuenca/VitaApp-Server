package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "subcategorias_subcategorias_personalizadas")
public class SubcategoriaSubcategoriaPersonalizada {
	@EmbeddedId
	private SubcategoriaSubcategoriaPersonalizadaPK id;

	public SubcategoriaSubcategoriaPersonalizadaPK getId() {
		return id;
	}

	public void setId(SubcategoriaSubcategoriaPersonalizadaPK id) {
		this.id = id;
	}
	
}
