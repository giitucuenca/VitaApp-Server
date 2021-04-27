package com.vitaapp.backend.tesis.domain;

public class PictogramCarer {
    private Integer pictogramCarerId;

    private String name;

    private String imageUrl;

    private Integer position;

    private Integer subcategoryId;

    private Integer pictogramId;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPictogramId() {
        return pictogramId;
    }

    public void setPictogramId(Integer pictogramId) {
        this.pictogramId = pictogramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }


    public Integer getPictogramCarerId() {
        return pictogramCarerId;
    }

    public void setPictogramCarerId(Integer pictogramCarerId) {
        this.pictogramCarerId = pictogramCarerId;
    }
}
