package com.vitaapp.backend.tesis.domain;

public class ImagePictogram {
    private Integer imageId;
    private String name;
    private String imageUrl;
    private Integer pictogramId;
    private Pictogram pictogram;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
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

    public Integer getPictogramId() {
        return pictogramId;
    }

    public void setPictogramId(Integer pictogramId) {
        this.pictogramId = pictogramId;
    }

    public Pictogram getPictogram() {
        return pictogram;
    }

    public void setPictogram(Pictogram pictogram) {
        this.pictogram = pictogram;
    }
}
