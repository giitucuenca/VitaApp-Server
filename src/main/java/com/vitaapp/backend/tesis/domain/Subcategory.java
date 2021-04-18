package com.vitaapp.backend.tesis.domain;


import java.util.List;

public class Subcategory {

	private Integer subcategoryId;

	private String name;

	private String description;
	
	private Integer categoryId;


	private String imageUrl;

	private String color;

	private boolean show;

	private List<ImageSubcategory> images;


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

	public List<ImageSubcategory> getImages() {
		return images;
	}

	public void setImages(List<ImageSubcategory> images) {
		this.images = images;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
