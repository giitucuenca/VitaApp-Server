package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.persistence.entity.Adulto;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ElderlyMapper {
    @Mappings( {
            @Mapping(source = "idAdulto", target = "elderlyId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "surname"),
            @Mapping(source = "codigo", target = "code"),
            @Mapping(source = "idCuidador", target = "carerId"),
            @Mapping(source = "genero", target = "gender"),
            @Mapping(source = "escolaridad", target = "scholarship"),
            @Mapping(source = "lateralidad", target = "laterality"),
    })
    Elderly toElderly(Adulto adulto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cuidador", ignore = true),
            @Mapping(target = "mensajes", ignore = true),
    })
    Adulto toAdulto(Elderly elderly);
}
