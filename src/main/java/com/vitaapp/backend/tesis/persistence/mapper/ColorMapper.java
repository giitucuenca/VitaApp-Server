package com.vitaapp.backend.tesis.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.vitaapp.backend.tesis.domain.ColorM;
import com.vitaapp.backend.tesis.persistence.entity.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper {
	@Mappings({
			@Mapping(source = "idColor", target = "colorId"),
			@Mapping(source = "color", target = "colorCode"),
			@Mapping(source = "nombre", target = "name"),
	})
	ColorM colorMTo(Color color);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(target = "categorias", ignore = true),
		@Mapping(target = "categoriasPersonalizadas", ignore = true)		
	})
	Color toColor(ColorM colorM);

}
