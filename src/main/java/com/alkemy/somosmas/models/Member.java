package com.alkemy.somosmas.models;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "members")
@Getter
@Setter
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
 
    @NotBlank
    @Column(nullable = false)
    private String name;

    private String facebookUrl;

    private String instagramUrl;

    private String linkedinUrl;

    @Column(nullable = false)
    private String image;

    private String description;

    private boolean deleted = Boolean.FALSE;

    private LocalDateTime createdDate;

    @PrePersist
    private void beforeaPersisting(){
        this.createdDate=LocalDateTime.now();
    }
 
}
