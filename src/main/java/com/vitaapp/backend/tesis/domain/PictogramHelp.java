package com.vitaapp.backend.tesis.domain;

import java.util.List;

public class PictogramHelp {
    private Integer pictogramId;
    private String name;
    private String imageUrl;
    private String color;
    private List<ImagePictogramHelp> images;


    public List<ImagePictogramHelp> getImages() {
        return images;
    }

    public void setImages(List<ImagePictogramHelp> images) {
        this.images = images;
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


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
