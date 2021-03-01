package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictogramas")
public class Pictograma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pictograma")
	private Integer idPictograma;
	private String nombre;
	private String imagen;
	@Column(name = "id_subcategoria")
	private Integer idSubcategoria;
	@ManyToOne
	@JoinColumn(name = "id_subcategoria", insertable = false, updatable = false)
	private Subcategoria subcategoria;
	
	public Integer getIdPictograma() {
		return idPictograma;
	}
	public void setIdPictograma(Integer idPictograma) {
		this.idPictograma = idPictograma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Integer getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(Integer idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
}
