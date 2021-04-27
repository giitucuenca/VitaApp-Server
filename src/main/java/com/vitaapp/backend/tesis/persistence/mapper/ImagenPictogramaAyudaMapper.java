package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.ImagePictogramHelp;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictogramaAyuda;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenPictogramaAyudaMapper {
    @Mappings({
            @Mapping(source = "idImagen", target = "imageId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idPictograma", target = "pictogramId"),
    })
    ImagePictogramHelp toImagePictogram(ImagenPictogramaAyuda imagenPictograma);
    List<ImagePictogramHelp> toImagesPictograms(List<ImagenPictogramaAyuda> imagenesPictogramas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pictograma", ignore = true)
    })
    ImagenPictogramaAyuda toImagenPictograma(ImagePictogramHelp imagePictogram);
}

