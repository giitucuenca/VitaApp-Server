package com.vitaapp.backend.tesis.domain;

public class Category {
	private Integer categoryId;
	private String name;
	private String description;
	private Integer colorId;
	private ColorM colorM;
	
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
	
	
}
