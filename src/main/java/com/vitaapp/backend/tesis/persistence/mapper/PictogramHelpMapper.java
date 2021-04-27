package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.PictogramHelp;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyuda;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImagenPictogramaAyudaMapper.class})
public interface PictogramHelpMapper {
    @Mappings({
            @Mapping(source = "idPictograma", target = "pictogramId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "imagenesPictogramas", target = "images"),
    })
    PictogramHelp toPictogram(PictogramaAyuda pictograma);
    List<PictogramHelp> toPictograms(List<PictogramaAyuda> pictogramas);

    @InheritInverseConfiguration
    @Mapping(target = "pictogramasPersonalizados", ignore = true)
    PictogramaAyuda toPictograma(PictogramHelp pictogram);
    List<PictogramaAyuda> toPictogramas(List<PictogramHelp> pictograms);
}
