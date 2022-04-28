package com.alkemy.somosmas.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE idUser=?")
@Where(clause = "delete = false")

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

    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createDate;

    private boolean deleted = Boolean.FALSE;

}