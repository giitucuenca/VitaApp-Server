package com.vitaapp.backend.tesis.persistence.mapper;


import com.vitaapp.backend.tesis.domain.Admin;
import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.persistence.entity.Administrador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mappings( {
            @Mapping(source = "idAdministrador", target = "adminId"),
            @Mapping(source = "correo", target = "email"),
            @Mapping(source = "contrasena", target = "password"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "surname"),
    })
    Admin toAdmin(Administrador administrador);

    @InheritInverseConfiguration
    Administrador toAdministrador(Admin admin);
}

