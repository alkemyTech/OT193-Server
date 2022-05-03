package com.alkemy.somosmas.ong.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDateTime createDate;
    @PrePersist
    private void beforePersisting() {
        this.createDate= LocalDateTime.now();
    }

}