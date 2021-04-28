package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.ElderlyCategory;
import com.vitaapp.backend.tesis.persistence.entity.AdultoCategoriaPersonalizada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElderlyCategoryMapper {

    @Mapping(source = "id.idCategoriaPersonalizada", target = "categoryId")
    @Mapping(source = "id.idAdulto", target = "elderlyId")
    ElderlyCategory toElderlyCategory(AdultoCategoriaPersonalizada adultoCategoria);
    List<ElderlyCategory> toElderlyCategoryList(List<AdultoCategoriaPersonalizada> adultoCategoria);

    @InheritInverseConfiguration
    //@Mapping(target = "categoriaPersonalizada", ignore = true)
    //@Mapping(target = "adulto", ignore = true)
    AdultoCategoriaPersonalizada toAdultoCategoria(ElderlyCategory adultoCategoria);
    List<AdultoCategoriaPersonalizada> toAdultoCategoriaList(List<ElderlyCategory> adultoCategoria);
}
