package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.PictogramHelperCarer;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaAyudaPersonalizado;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PictogramHelperCarerMapper {
    @Mappings({
            @Mapping(source = "idPictogramaPersonalizado", target = "pictogramCarerId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "posicion", target = "position"),
            @Mapping(source = "idPictograma", target = "pictogramId"),
            @Mapping(source = "idAyuda", target = "helperId"),
            @Mapping(target = "color", ignore = true)
    })
    PictogramHelperCarer toPictogram(PictogramaAyudaPersonalizado pictograma);
    List<PictogramHelperCarer> toPictograms(List<PictogramaAyudaPersonalizado> pictogramas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pictograma", ignore = true),
            @Mapping(target = "ayuda", ignore = true),
    })
    PictogramaAyudaPersonalizado toPictograma(PictogramHelperCarer pictogram);
    List<PictogramaAyudaPersonalizado> toPictogramas(List<PictogramHelperCarer> pictograms);
}
