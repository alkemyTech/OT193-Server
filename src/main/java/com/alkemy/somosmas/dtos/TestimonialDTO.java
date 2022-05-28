package com.alkemy.somosmas.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TestimonialDTO {

    private Long id;

    @NotBlank(message = "Debe agregar un Nombre")
    private String name;

    private String image;

    @NotBlank(message = "Debe agregar un contenido")
    private String content;

}
