package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.domain.Elderly;
import com.vitaapp.backend.tesis.persistence.entity.Adulto;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElderlyMapper {
    @Mappings( {
            @Mapping(source = "idAdulto", target = "elderlyId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "surname"),
            @Mapping(source = "codigo", target = "code"),
            @Mapping(source = "idCuidador", target = "carerId"),
            @Mapping(source = "genero", target = "gender"),
            @Mapping(source = "idEscolaridad", target = "scholarityId"),
            @Mapping(source = "lateralidad", target = "laterality"),
            @Mapping(source = "correo", target = "email"),
            @Mapping(source = "contrasena", target = "password")
    })
    Elderly toElderly(Adulto adulto);
    List<Elderly> toElderlies(List<Adulto> adultos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cuidador", ignore = true),
            @Mapping(target = "mensajes", ignore = true),
            @Mapping(target = "escolaridad", ignore = true)
    })
    Adulto toAdulto(Elderly elderly);
}
