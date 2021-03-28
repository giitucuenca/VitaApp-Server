package com.vitaapp.backend.tesis.persistence.mapper;

import com.vitaapp.backend.tesis.domain.Category;
import com.vitaapp.backend.tesis.domain.ImageCategory;
import com.vitaapp.backend.tesis.persistence.entity.Categoria;
import com.vitaapp.backend.tesis.persistence.entity.ImagenCategoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenCategoriaMapper {
    @Mappings({
            @Mapping(source = "idImagen", target = "imageId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "imagenUrl", target = "imageUrl"),
            @Mapping(source = "idCategoria", target = "categoryId")
    })
    ImageCategory toImageCategory(ImagenCategoria imagenCategoria);
    List<ImageCategory> toImagesCategories(List<ImagenCategoria> imagenesCategorias);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "categoria", ignore = true)
    })
    ImagenCategoria toImagenCategoria(ImageCategory imageCategory);
}
