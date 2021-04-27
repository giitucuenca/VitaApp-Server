package com.vitaapp.backend.tesis.domain;

public class CategoryCarer {
    private Integer categoryCarerId;

    private Integer categoryId;

    private String name;

    private String description;

    private String color;

    private String imageUrl;

    private Integer carerId;

    private Integer helperId;

    public Integer getHelperId() {
        return helperId;
    }

    public void setHelperId(Integer helperId) {
        this.helperId = helperId;
    }

    public Integer getCarerId() {
        return carerId;
    }

    public void setCarerId(Integer carerId) {
        this.carerId = carerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCategoryCarerId() {
        return categoryCarerId;
    }

    public void setCategoryCarerId(Integer categoryCarerId) {
        this.categoryCarerId = categoryCarerId;
    }
}
