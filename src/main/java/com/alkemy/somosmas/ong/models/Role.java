package com.alkemy.somosmas.ong.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="firstName")
    private String description;

    @Column(name = "create_at")
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDate createAt;

}