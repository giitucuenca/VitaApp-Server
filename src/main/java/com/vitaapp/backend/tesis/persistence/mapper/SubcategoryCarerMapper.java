package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Subcategory;
import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.persistence.entity.Subcategoria;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.persistence.Entity;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SubcategoryCarerMapper {
    @Mappings({
            @Mapping(source = "idSubcategoriaPersonalizada", target = "subcategoryCarerId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idSubcategoria", target = "subcategoryId"),
            @Mapping(source = "idCategoriaPersonalizada", target = "categoryId"),
    })
    SubcategoryCarer toSubcategory(SubcategoriaPersonalizada subcategoria);

    List<SubcategoryCarer> toSubcategories(List<SubcategoriaPersonalizada> subcategorias);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "subcategoria", ignore = true),
            @Mapping(target = "categoriaPersonalizada", ignore = true),
            @Mapping(target = "pictogramasPersonalizados", ignore = true),

    })
    SubcategoriaPersonalizada toSubcategoria(SubcategoryCarer subcategory);
}
