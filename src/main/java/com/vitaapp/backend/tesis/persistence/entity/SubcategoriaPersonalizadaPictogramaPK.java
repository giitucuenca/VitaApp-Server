package com.vitaapp.backend.tesis.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubcategoriaPersonalizadaPictogramaPK implements Serializable {
	@Column(name = "id_subcategoria_personalizada")
	private Integer idSubcategoriaPersonalizada;
	@Column(name = "id_pictograma")
	private Integer idPictograma;
	public Integer getIdSubcategoriaPersonalizada() {
		return idSubcategoriaPersonalizada;
	}
	public void setIdSubcategoriaPersonalizada(Integer idSubcategoriaPersonalizada) {
		this.idSubcategoriaPersonalizada = idSubcategoriaPersonalizada;
	}
	public Integer getIdPictograma() {
		return idPictograma;
	}
	public void setIdPictograma(Integer idPictograma) {
		this.idPictograma = idPictograma;
	}
	
}
