package com.vitaapp.backend.tesis.domain;

import java.util.List;

import com.vitaapp.backend.tesis.persistence.entity.CategoriaPersonalizada;

public class SubcategoryCarer {
    private Integer subcategoryCarerId;
    private Integer subcategoryId;
    private String name;
    private String description;
    private Integer categoryId;
    private String color;
    private String imageUrl;

    public SubcategoryCarer() {
    }

    public SubcategoryCarer(Subcategory subcategory, CategoriaPersonalizada categoria) {
        this.subcategoryId = subcategory.getSubcategoryId();
        this.name = subcategory.getName();
        this.description = subcategory.getDescription();
        this.categoryId = categoria.getIdCategoriaPersonalizada();
        this.color = subcategory.getColor();
        this.imageUrl = subcategory.getImageUrl();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSubcategoryCarerId() {
        return subcategoryCarerId;
    }

    public void setSubcategoryCarerId(Integer subcategoryCarerId) {
        this.subcategoryCarerId = subcategoryCarerId;
    }

}
