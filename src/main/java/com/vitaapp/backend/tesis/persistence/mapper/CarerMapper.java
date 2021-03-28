package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Carer;
import com.vitaapp.backend.tesis.persistence.entity.Cuidador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CarerMapper {
    @Mappings( {
            @Mapping(source = "idCuidador", target = "carerId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "surname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
    })
    Carer toCarer(Cuidador cuidador);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "adultos", ignore = true),
            @Mapping(target = "mensajes", ignore = true),
            @Mapping(target = "categoriasPersonalizadas", ignore = true),
    })
    Cuidador toCuidador(Carer carer);
}
