package com.vitaapp.backend.tesis.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface SubcategoryMapper  {

	@Mappings({
		@Mapping(source = "idSubcategoria", target = "subcategoryId"),
		@Mapping(source = "nombre", target = "name"),
		@Mapping(source = "descripcion", target = "description"),
		@Mapping(source = "idCategoria", target = "categoryId"),
		@Mapping(source = "categoria", target = "category"),
	})
	Subcategory toSubcategory(Subcategoria subcategoria);
	
	
	@InheritInverseConfiguration
	@Mapping(target = "pictogramas", ignore = true)
	Subcategoria toSubcategoria(Subcategory subcategory);
}
