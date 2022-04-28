package com.alkemy.somosmas.models;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name="firstName",nullable = false)
    private String firstName;

    @Column(name="lastName",nullable = false)
    private String lastName;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="photo")
    private String photo;

    @Column(name="roleId")
    private Long roleId;

}