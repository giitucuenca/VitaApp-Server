package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mensaje")
	private Integer idMensaje;
	
	@Column(name = "id_adulto")
	private Integer idAdulto;
	@Column(name = "id_cuidador")
	private Integer idCuidador;

	public Integer getIdAdulto() {
		return idAdulto;
	}

	public void setIdAdulto(Integer idAdulto) {
		this.idAdulto = idAdulto;
	}

	public Integer getIdCuidador() {
		return idCuidador;
	}

	public void setIdCuidador(Integer idCuidador) {
		this.idCuidador = idCuidador;
	}

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}
	
}
