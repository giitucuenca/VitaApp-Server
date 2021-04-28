package com.vitaapp.backend.tesis.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "adultos_categorias_personalizadas")
public class AdultoCategoriaPersonalizada {
	
	@EmbeddedId
	private AdultoCategoriaPersonalizadaPK id;

	/*@ManyToOne
	@MapsId("idAdulto")
	@JoinColumn(name = "id_adulto", insertable = false, updatable = false)
	private Adulto adulto;

	@ManyToOne
	@MapsId("idCategoriaPersonalizada")
	@JoinColumn(name = "id_categoria_personalizada", insertable = false, updatable = false)
	private CategoriaPersonalizada categoriaPersonalizada;
	*/


	public AdultoCategoriaPersonalizadaPK getId() {
		return id;
	}

	public void setId(AdultoCategoriaPersonalizadaPK id) {
		this.id = id;
	}


}
