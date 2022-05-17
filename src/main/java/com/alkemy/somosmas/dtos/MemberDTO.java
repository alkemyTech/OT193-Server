package com.alkemy.somosmas.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

	private Long id;
	
	private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;
    
    private String image;

    private String description;

}
