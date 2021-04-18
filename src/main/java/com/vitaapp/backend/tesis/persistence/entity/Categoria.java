package com.vitaapp.backend.tesis.persistence.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Integer idCategoria;

	private String nombre;

	private String descripcion;

	private boolean mostrar;

	@Column(name = "id_color")
	private Integer idColor;

	@Column(name= "imagen_url")
	private String imagenUrl;
	
	@ManyToOne
	@JoinColumn(name = "id_color",insertable = false, updatable = false)
	private Color color;
	
	@OneToMany(mappedBy = "categoria")
	private List<Subcategoria> subcategorias;
	
	@OneToMany(mappedBy = "categoria")
	private List<CategoriaPersonalizada> categoriasPersonalizadas;

	@OneToMany(mappedBy = "categoria")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ImagenCategoria> imagenesCategorias;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIdColor() {
		return idColor;
	}

	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public List<CategoriaPersonalizada> getCategoriasPersonalizadas() {
		return categoriasPersonalizadas;
	}

	public void setCategoriasPersonalizadas(List<CategoriaPersonalizada> categoriasPersonalizadas) {
		this.categoriasPersonalizadas = categoriasPersonalizadas;
	}

	public List<ImagenCategoria> getImagenesCategorias() {
		return imagenesCategorias;
	}

	public void setImagenesCategorias(List<ImagenCategoria> imagenesCategorias) {
		this.imagenesCategorias = imagenesCategorias;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
}
