package com.alkemy.somosmas.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private Long categoryId;


}
