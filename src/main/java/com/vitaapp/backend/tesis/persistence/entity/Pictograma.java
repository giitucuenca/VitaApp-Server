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

	@Column(name = "imagen_url")
	private String imagenURL;

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

	public String getImagenURL() {
		return imagenURL;
	}

	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
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
