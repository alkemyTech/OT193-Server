package com.alkemy.somosmas.models;

import lombok.Data;
import javax.persistence.*;
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

    @Column(name="description")
    private String description;

//    private LocalDateTime createDate;
//    
//    @PrePersist
//    private void beforePersisting() {
//        this.createDate= LocalDateTime.now();
//    }


}