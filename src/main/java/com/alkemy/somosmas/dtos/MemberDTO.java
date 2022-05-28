package com.alkemy.somosmas.dtos;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	private Long id;
	
	@NotBlank
	private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;
    
    private String image;

    private String description;

}
