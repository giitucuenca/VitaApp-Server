package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Pictogram;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.entity.Pictograma;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SubcategoryMapper.class, ImagenPictogramaMapper.class})
public interface PictogramMapper {

    @Mappings({
            @Mapping(source = "idPictograma", target = "pictogramId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idSubcategoria", target = "subcategoryId"),
            @Mapping(source = "imagenesPictogramas", target = "images"),
            @Mapping(target = "color", ignore = true)
    })
    Pictogram toPictogram(Pictograma pictograma);
    List<Pictogram> toPictograms(List<Pictograma> pictogramas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "subcategoria", ignore = true),
            @Mapping(target = "pictogramasPersonalizados", ignore = true),
    })
    Pictograma toPictograma(Pictogram pictogram);
}
