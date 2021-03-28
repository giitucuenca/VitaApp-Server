package com.vitaapp.backend.tesis.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MensajePictogramaPK implements Serializable {
	@Column(name = "id_mensaje")
	private Integer idMensaje;
	@Column(name = "id_pictograma_personalizado")
	private Integer idPictogramaPersonalizado;

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}

	public Integer getIdPictogramaPersonalizado() {
		return idPictogramaPersonalizado;
	}

	public void setIdPictogramaPersonalizado(Integer idPictogramaPersonalizado) {
		this.idPictogramaPersonalizado = idPictogramaPersonalizado;
	}
}
