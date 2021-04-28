package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.CategoryCarer;
import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryCarerMapper {
    @Mappings({
            @Mapping(source = "idCategoriaPersonalizada", target = "categoryCarerId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "idCuidador", target = "carerId"),
            @Mapping(source = "idAyuda", target = "helperId")
    })
    CategoryCarer toCategory(CategoriaPersonalizada categoria);
    List<CategoryCarer> toCategories(List<CategoriaPersonalizada> categorias);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "subcategoriasPersonalizadas", ignore = true),
            //@Mapping(target = "adultoCategoriaPersonalizadas", ignore = true),
            @Mapping(target = "categoria", ignore = true),
            @Mapping(target = "cuidador", ignore = true),
            @Mapping(target = "ayuda", ignore = true)

    })
    CategoriaPersonalizada toCategoria(CategoryCarer category);
    List<CategoriaPersonalizada> toCategorias(List<CategoryCarer> categories);
}
