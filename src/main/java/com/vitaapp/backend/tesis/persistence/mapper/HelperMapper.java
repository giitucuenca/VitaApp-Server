package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Helper;
import com.vitaapp.backend.tesis.persistence.entity.Ayuda;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HelperMapper {
    @Mapping(source = "idAyuda", target = "helperId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "imagenUrl", target = "imageUrl")
    Helper toHelper(Ayuda ayuda);
    List<Helper> toHelpers(List<Ayuda> ayudas);

    @InheritInverseConfiguration
    @Mapping(target = "pictogramas", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    Ayuda toAyuda(Helper helper);
}
