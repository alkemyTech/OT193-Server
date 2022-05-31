package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
    private List<SlideGetDTO> slidesList = new ArrayList<>();

}
