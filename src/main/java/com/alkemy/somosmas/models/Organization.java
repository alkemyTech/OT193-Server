package com.alkemy.somosmas.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="organizations")
/*Entiendo debo agregar Getters y Setters */
@Getter
@Setter
@SQLDelete(sql="UPDATE organizations SET deleted= true WHERE id=?")
@Where(clause="deleted=false")
public class Organization {


    /*Si bien los requerimientos no lo indican entiendo debe tener un id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String image;
    private String address;
    private int phone;
    @Column(nullable = false)
    private String email;

    /* REVISAR tipo TEXT
     https://stackoverflow.com/questions/3868096/jpa-how-do-i-persist-a-string-into-a-database-field-type-mysql-text
    * */
    @Column(nullable = false, columnDefinition = "TEXT")

    private String welcomeText;

    @Column(columnDefinition = "TEXT")
    private String aboutUsText;


    private boolean deleted=Boolean.FALSE;

    //Incidencia OT193-63
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    /*Pensaba que podria implementarse tambien con un HASHMAP
    HashMap<String, String> socialMedia = new HashMap<String, String>();

     */
    @OneToMany(mappedBy="organization")
    @OrderBy("order")
    private List<Slide> slidesList = new ArrayList<>();


    private LocalDateTime createDate;

    @PrePersist
    private void beforePersisting() {
        this.createDate= LocalDateTime.now();
    }



}
