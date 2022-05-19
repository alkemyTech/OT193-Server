package com.alkemy.somosmas.dtos;

import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private Long id;

    @NotNull(message = "No debe quedar vacío")
    @Size(min = 1)
    private String name;

    private String description;
    private String image;
}
