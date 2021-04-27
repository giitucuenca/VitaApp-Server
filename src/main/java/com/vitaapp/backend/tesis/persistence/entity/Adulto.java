package com.vitaapp.backend.tesis.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "adultos")
public class Adulto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adulto")
	private Integer idAdulto;
	@Column(length = 60)
	@NotBlank(message = "Debe ingresar un nombre")
	private String nombre;
	@Column(length = 60)
	@NotBlank(message = "Debe ingresar un apellido")
	private String apellido;
	@NotBlank(message = "Debe ingresar un nombre de usuario")
	private String userName;

	@Column(name = "id_cuidador")
	private Integer idCuidador;
	private char genero;

	@Column(name = "id_escolaridad")
	@NotNull(message = "Debe ingresar la escolaridad")
	private Integer idEscolaridad;

	@NotBlank(message = "Debe ingresar la laterabilidad")
	private char lateralidad;

	@ManyToOne
	@JoinColumn(name = "id_cuidador", insertable = false, updatable = false)
	private Cuidador cuidador;

	@ManyToOne
	@JoinColumn(name = "id_escolaridad", insertable = false, updatable = false)
	private NivelEscolaridad escolaridad;
	
	@OneToMany(mappedBy = "adulto", cascade = CascadeType.ALL)
	private List<Mensaje> mensajes;

	public char getLateralidad() {
		return lateralidad;
	}

	public void setLateralidad(char lateralidad) {
		this.lateralidad = lateralidad;
	}

	public Integer getIdAdulto() {
		return idAdulto;
	}
	public void setIdAdulto(Integer idAdulto) {
		this.idAdulto = idAdulto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIdCuidador() {
		return idCuidador;
	}
	public void setIdCuidador(Integer idCuidador) {
		this.idCuidador = idCuidador;
	}
	
	public Cuidador getCuidador() {
		return cuidador;
	}
	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Integer getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(Integer idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public NivelEscolaridad getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(NivelEscolaridad escolaridad) {
		this.escolaridad = escolaridad;
	}

}
