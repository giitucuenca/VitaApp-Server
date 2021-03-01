package com.vitaapp.backend.tesis.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;

@Mapper(componentModel = "spring", uses = {ColorMapper.class})
public interface CategoryMapper {
	@Mappings({
		@Mapping(source = "idCategoria", target = "categoryId"),
		@Mapping(source = "nombre", target = "name"),
		@Mapping(source = "descripcion", target = "description"),
		@Mapping(source = "idColor", target = "colorId"),
		@Mapping(source = "color", target = "colorM")
	})
	Category toCategory(Categoria categoria);
	List<Category> toCategories(List<Categoria> categorias);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(target = "subcategorias", ignore = true),
		@Mapping(target = "categoriasPersonalizadas", ignore = true)		
	})
	Categoria toCategoria(Category category);
}
