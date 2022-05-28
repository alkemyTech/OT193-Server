package com.alkemy.somosmas.dtos;

import com.alkemy.somosmas.models.Activity;
import com.alkemy.somosmas.models.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ActivityDTO {
    private Long id;

    @NotNull(message = "Ingrese un nombre")
    @Size(min = 1)
    private String name;

    @NotNull(message = "Ingrese el contenido")
    @Size(min = 1)
    private String content;
}
