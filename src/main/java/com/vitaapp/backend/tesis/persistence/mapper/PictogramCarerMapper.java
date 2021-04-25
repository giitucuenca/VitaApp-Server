package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.PictogramCarer;
import com.vitaapp.backend.tesis.domain.SubcategoryCarer;
import com.vitaapp.backend.tesis.persistence.entity.PictogramaPersonalizado;
import com.vitaapp.backend.tesis.persistence.entity.SubcategoriaPersonalizada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PictogramCarerMapper {
    @Mappings({
            @Mapping(source = "idPictogramaPersonalizado", target = "pictogramCarerId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "posicion", target = "position"),
            @Mapping(source = "mostrar", target = "show"),
            @Mapping(source = "idPictograma", target = "pictogramId"),
            @Mapping(source = "idSubcategoriaPersonalizada", target = "subcategoryId"),
    })
    PictogramCarer toPictogram(PictogramaPersonalizado pictograma);

    List<PictogramCarer> toPictograms(List<PictogramaPersonalizado> pictogramas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pictograma", ignore = true),
            @Mapping(target = "subcategoriaPersonalizada", ignore = true),
    })
    PictogramaPersonalizado toPictograma(PictogramCarer pictogram);
    List<PictogramaPersonalizado> toPictogramas(List<PictogramCarer> pictograms);
}
