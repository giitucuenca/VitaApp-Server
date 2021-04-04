package com.vitaapp.backend.tesis.domain;

import java.util.List;

public class SubcategoryCarer {
    private Integer subcategoryCarerId;

    private Integer subcategoryId;

    private String name;

    private String description;

    private Integer categoryId;

    private CategoryCarer category;

    private String imageUrl;


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

    public CategoryCarer getCategory() {
        return category;
    }

    public void setCategory(CategoryCarer category) {
        this.category = category;
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
