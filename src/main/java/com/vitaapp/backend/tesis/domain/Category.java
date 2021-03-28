package com.vitaapp.backend.tesis.domain;

import java.util.List;

public class Category {
	private Integer categoryId;

	private String name;

	private String description;

	private Integer colorId;

	private ColorM colorM;

	private String imageUrl;

	private List<ImageCategory> imagesCategories;


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

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public ColorM getColorM() {
		return colorM;
	}

	public void setColorM(ColorM colorM) {
		this.colorM = colorM;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ImageCategory> getImagesCategories() {
		return imagesCategories;
	}

	public void setImagesCategories(List<ImageCategory> imagesCategories) {
		this.imagesCategories = imagesCategories;
	}
}
