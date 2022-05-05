package com.alkemy.somosmas.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Long roleID;
}
