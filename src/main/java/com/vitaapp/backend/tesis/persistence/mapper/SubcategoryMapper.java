package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ImagenSubcategoriaMapper.class})
public interface SubcategoryMapper {

    @Mappings({
            @Mapping(source = "idSubcategoria", target = "subcategoryId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "idCategoria", target = "categoryId"),
			@Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "imagenesSubcategorias", target = "images"),
            @Mapping(source = "mostrar", target = "show"),
            @Mapping(target = "color", ignore = true)
    })
    Subcategory toSubcategory(Subcategoria subcategoria);

    List<Subcategory> toSubcategories(List<Subcategoria> subcategorias);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pictogramas", ignore = true),
            @Mapping(target = "subcategoriasPersonalizadas", ignore = true),
            @Mapping(target = "categoria", ignore = true)
    })
    Subcategoria toSubcategoria(Subcategory subcategory);
}
