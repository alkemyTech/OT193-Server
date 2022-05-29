package com.alkemy.somosmas.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name= "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    private LocalDateTime createDate;

    public Role(Long id){
        this.id = id;
    }
    public Role(){

    }
    @PrePersist
    private void beforePersisting() {
        this.createDate= LocalDateTime.now();
    }

}