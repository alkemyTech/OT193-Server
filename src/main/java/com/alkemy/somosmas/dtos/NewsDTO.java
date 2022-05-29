package com.alkemy.somosmas.dtos;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {


	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String content;
	@NotBlank
	private String image;
	
	private Long categoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategory(Long categoryId) {
		this.categoryId = categoryId;
	}

}
