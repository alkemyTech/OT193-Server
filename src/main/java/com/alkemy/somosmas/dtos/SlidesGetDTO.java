package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlidesGetDTO {
    private String imageUrl;
	private Integer order_ong;
	
	public SlidesGetDTO() {
		super();
	}

	public SlidesGetDTO(String imageUrl, Integer order_ong) {
		super();
		this.imageUrl = imageUrl;
		this.order_ong = order_ong;
	}
}
