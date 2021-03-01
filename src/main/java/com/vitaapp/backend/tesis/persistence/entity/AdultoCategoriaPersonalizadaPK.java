package com.vitaapp.backend.tesis.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AdultoCategoriaPersonalizadaPK implements Serializable{
	@Column(name = "id_adulto")
	private Integer idAdulto;
	@Column(name = "id_categoria_personalizada")
	private Integer idCategoriaPersonalizada;
	public Integer getIdAdulto() {
		return idAdulto;
	}
	public void setIdAdulto(Integer idAdulto) {
		this.idAdulto = idAdulto;
	}
	public Integer getIdCategoriaPersonalizada() {
		return idCategoriaPersonalizada;
	}
	public void setIdCategoriaPersonalizada(Integer idCategoriaPersonalizada) {
		this.idCategoriaPersonalizada = idCategoriaPersonalizada;
	}
}
