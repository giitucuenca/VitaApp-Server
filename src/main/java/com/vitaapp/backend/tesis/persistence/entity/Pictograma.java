package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pictogramas")
public class Pictograma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pictograma")
	private Integer idPictograma;

	private String nombre;

	@Column(name = "imagen_url")
	private String imagenUrl;

	@Column(name = "id_subcategoria")
	private Integer idSubcategoria;

	@ManyToOne
	@JoinColumn(name = "id_subcategoria", insertable = false, updatable = false)
	private Subcategoria subcategoria;

	@OneToMany(mappedBy = "pictograma")
	List<PictogramaPersonalizado> pictogramasPersonalizados;

	@OneToMany(mappedBy = "pictograma")
	private List<ImagenPictograma> imagenesPictogramas;

	public List<ImagenPictograma> getImagenesPictogramas() {
		return imagenesPictogramas;
	}

	public void setImagenesPictogramas(List<ImagenPictograma> imagenesPictogramas) {
		this.imagenesPictogramas = imagenesPictogramas;
	}

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

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
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

	public List<PictogramaPersonalizado> getPictogramasPersonalizados() {
		return pictogramasPersonalizados;
	}

	public void setPictogramasPersonalizados(List<PictogramaPersonalizado> pictogramasPersonalizados) {
		this.pictogramasPersonalizados = pictogramasPersonalizados;
	}
}
