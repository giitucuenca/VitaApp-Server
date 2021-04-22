package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.Scholarity;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.entity.NivelEscolaridad;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScholarityMapper {
    @Mappings({
            @Mapping(source = "idEscolaridad", target = "scholarityId"),
            @Mapping(source = "escolaridad", target = "scholarity"),

    })
    Scholarity toScholarity(NivelEscolaridad escolaridad);
    List<Scholarity> toShocholarityLevels(List<NivelEscolaridad> nivelesEscolarida);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "adultos", ignore = true)
    })
    NivelEscolaridad toScholarity(Scholarity scholarity);

}
