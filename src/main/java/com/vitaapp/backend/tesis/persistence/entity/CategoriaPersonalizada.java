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
@Table(name = "categorias_personalizadas")
public class CategoriaPersonalizada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria_personalizada")
	private Integer idCategoriaPersonalizada;
	private String nombre;
	private String descripcion;
	@Column(name = "id_color")
	private Integer idColor;
	@Column(name = "id_cuidador")
	private Integer idCuidador;
	@Column(name = "id_categoria")
	private Integer idCategoria;
	
	@ManyToOne
	@JoinColumn(name = "id_cuidador", updatable = false, insertable = false)
	private Cuidador cuidador;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria", updatable = false, insertable = false)
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_color", updatable = false, insertable = false)
	private Color color;
	
	public Integer getIdCategoriaPersonalizada() {
		return idCategoriaPersonalizada;
	}
	public void setIdCategoriaPersonalizada(Integer idCategoriaPersonalizada) {
		this.idCategoriaPersonalizada = idCategoriaPersonalizada;
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
	public Integer getIdCuidador() {
		return idCuidador;
	}
	public void setIdCuidador(Integer idCuidador) {
		this.idCuidador = idCuidador;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Cuidador getCuidador() {
		return cuidador;
	}
	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Integer getIdColor() {
		return idColor;
	}
	public void setIdColor(Integer idColor) {
		this.idColor = idColor;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
}
