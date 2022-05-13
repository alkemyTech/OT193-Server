package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrganizationDTO {

    private Long id;

    private String name;

    private String image;
    private String address;
    private int phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private LocalDateTime createDate;


}
