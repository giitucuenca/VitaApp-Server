package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenPictogramaMapper {
    @Mappings({
            @Mapping(source = "idImagen", target = "imageId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idPictograma", target = "pictogramId"),
    })
    ImagePictogram toImagePictogram(ImagenPictograma imagenPictograma);
    List<ImagePictogram> toImagesPictograms(List<ImagenPictograma> imagenesPictogramas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pictograma", ignore = true)
    })
    ImagenPictograma toImagenPictograma(ImagePictogram imagePictogram);
}
