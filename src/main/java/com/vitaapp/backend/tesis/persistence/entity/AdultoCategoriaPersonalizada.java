package com.vitaapp.backend.tesis.persistence.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "adultos_categorias_personalizadas")
public class AdultoCategoriaPersonalizada {
	
	@EmbeddedId
	private AdultoCategoriaPersonalizadaPK id;

	public AdultoCategoriaPersonalizadaPK getId() {
		return id;
	}

	public void setId(AdultoCategoriaPersonalizadaPK id) {
		this.id = id;
	}
}
