package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrganizationBasicDTO {

    private String name;
    private String image;
    private String address;
    private int phone;
    private String facebookUrl;
    private String linkedinUrl;
    private String instagramUrl;

}
