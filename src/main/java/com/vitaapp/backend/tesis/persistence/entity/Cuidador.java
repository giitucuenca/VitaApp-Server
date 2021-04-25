package com.vitaapp.backend.tesis.persistence.entity;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Cuidadores")
public class Cuidador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuidador")
	private Integer idCuidador;
	@Column(length = 40)
	@NotBlank(message = "Debe ingresar un nombre")
	private String nombre;
	@Column(length = 40)
	@NotBlank(message = "Debe ingresar un apellido")
	private String apellido;
	@Column(length = 40, unique = true)
	@NotBlank(message = "Debe ingresar un correo")
	@Email(message = "Email no valido")
	private String correo;
	@NotBlank(message = "Debe ingresar una contraseña")
	private String password;
	
	@OneToMany(mappedBy = "cuidador")
	private List<Adulto> adultos;
	
	@OneToMany(mappedBy = "cuidador")
	private List<Mensaje> mensajes;
	
	@OneToMany(mappedBy = "cuidador")
	private List<CategoriaPersonalizada> categoriasPersonalizadas;
	
	public Integer getIdCuidador() {
		return idCuidador;
	}
	public void setIdCuidador(Integer idCuidador) {
		this.idCuidador = idCuidador;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Adulto> getAdultos() {
		return adultos;
	}
	public void setAdultos(List<Adulto> adultos) {
		this.adultos = adultos;
	}
	public List<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public List<CategoriaPersonalizada> getCategoriasPersonalizadas() {
		return categoriasPersonalizadas;
	}
	public void setCategoriasPersonalizadas(List<CategoriaPersonalizada> categoriasPersonalizadas) {
		this.categoriasPersonalizadas = categoriasPersonalizadas;
	}
	
	
}
