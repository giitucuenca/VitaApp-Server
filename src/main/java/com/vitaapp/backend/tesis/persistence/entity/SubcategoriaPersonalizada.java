package com.vitaapp.backend.tesis.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subcategorias_personalizadas")
public class SubcategoriaPersonalizada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subcategoria_personalizada")
	private Integer idSubcategoriaPersonalizada;
    private String nombre;
    private String descripcion;
    private String imagenUrl;

    @Column(name = "id_subcategoria")
    private Integer idSubcategoria;

    @Column(name = "id_categoria_personalizada")
	private Integer idCategoriaPersonalizada;

    @ManyToOne
	@JoinColumn(name = "id_categoria_personalizada", insertable = false, updatable = false)
	private CategoriaPersonalizada categoriaPersonalizada;

    @OneToMany(mappedBy = "subcategoriaPersonalizada")
    private List<PictogramaPersonalizado> pictogramasPersonalizados;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", insertable = false, updatable = false)
    private Subcategoria subcategoria;

    public Integer getIdSubcategoriaPersonalizada() {
        return idSubcategoriaPersonalizada;
    }

    public void setIdSubcategoriaPersonalizada(Integer idSubcategoriaPersonalizada) {
        this.idSubcategoriaPersonalizada = idSubcategoriaPersonalizada;
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

	public Integer getIdCategoriaPersonalizada() {
		return idCategoriaPersonalizada;
	}

	public void setIdCategoriaPersonalizada(Integer idCategoriaPersonalizada) {
		this.idCategoriaPersonalizada = idCategoriaPersonalizada;
	}

	public CategoriaPersonalizada getCategoriaPersonalizada() {
		return categoriaPersonalizada;
	}

	public List<PictogramaPersonalizado> getPictogramasPersonalizados() {
		return pictogramasPersonalizados;
	}

	public void setPictogramasPersonalizados(List<PictogramaPersonalizado> pictogramasPersonalizados) {
		this.pictogramasPersonalizados = pictogramasPersonalizados;
	}

	public void setCategoriaPersonalizada(CategoriaPersonalizada categoriaPersonalizada) {
		this.categoriaPersonalizada = categoriaPersonalizada;
	}

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
