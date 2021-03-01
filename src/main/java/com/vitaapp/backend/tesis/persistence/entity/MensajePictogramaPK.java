package com.vitaapp.backend.tesis.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MensajePictogramaPK implements Serializable {
	@Column(name = "id_mensaje")
	private Integer idMensaje;
	@Column(name = "id_pictograma")
	private Integer idPictograma;
	public Integer getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}
	public Integer getIdPictograma() {
		return idPictograma;
	}
	public void setIdPictograma(Integer idPictograma) {
		this.idPictograma = idPictograma;
	}
	
}
