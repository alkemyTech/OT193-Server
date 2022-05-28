package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrganizationDTO {


    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @NotBlank
    private String address;
    @Min(0)
    @Max(1999999999)
    private int phone;
    @NotBlank
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank
    private String welcomeText;
    @NotBlank
    private String aboutUsText;
    private LocalDateTime createDate;

    @NotBlank
    private String facebookUrl;
    @NotBlank
    private String linkedinUrl;
    @NotBlank
    private String instagramUrl;


}
