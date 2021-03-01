package com.vitaapp.backend.tesis.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubcategoriaSubcategoriaPersonalizadaPK implements Serializable{
	@Column(name = "id_subcategoria_personalizada")
	private Integer idSubcategoriaPersonalizada;
	@Column(name = "id_subcategoria")
	private Integer idSubcategoria;
	public Integer getIdSubcategoriaPersonalizada() {
		return idSubcategoriaPersonalizada;
	}
	public void setIdSubcategoriaPersonalizada(Integer idSubcategoriaPersonalizada) {
		this.idSubcategoriaPersonalizada = idSubcategoriaPersonalizada;
	}
	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	
}
