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

@Entity
@Table(name = "adultos")
public class Adulto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adulto")
	private Integer idAdulto;
	@Column(length = 40)
	private String nombre;
	@Column(length = 40)
	private String apellido;
	private String codigo;
	@Column(name = "id_cuidador")
	private Integer idCuidador;
	private char genero;
	private String escolaridad;
	private String laterabilidad;
	
	@ManyToOne
	@JoinColumn(name = "id_cuidador", insertable = false, updatable = false)
	private Cuidador cuidador;
	
	@OneToMany(mappedBy = "adulto", cascade = CascadeType.ALL)
	private List<Mensaje> mensajes;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public String getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	public String getLaterabilidad() {
		return laterabilidad;
	}
	public void setLaterabilidad(String laterabilidad) {
		this.laterabilidad = laterabilidad;
	}
	
	
	
	
}
