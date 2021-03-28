package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.domain.ImagePictogram;
import com.vitaapp.backend.tesis.domain.ImageSubcategory;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import com.vitaapp.backend.tesis.persistence.entity.ImagenPictograma;
import com.vitaapp.backend.tesis.persistence.entity.ImagenSubcategoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ImagenSubcategoriaMapper {
    @Mappings({
            @Mapping(source = "idImagen", target = "imageId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idSubcategoria", target = "subcategoryId"),
    })
    ImageSubcategory toImageSubcategory(ImagenSubcategoria imagenSubcategoria);
    List<ImageSubcategory> toImagesSubcategories(List<ImagenSubcategoria> imagenesSubcategorias);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "subcategoria", ignore = true)
    })
    ImagenSubcategoria toImagenSubcategoria(ImageSubcategory imageSubcategory);
}
