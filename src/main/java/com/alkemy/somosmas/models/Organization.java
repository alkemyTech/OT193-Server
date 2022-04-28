package com.alkemy.somosmas.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

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
    /* En la preaceleracion usamos este tipo de generacion
    @GeneratedValue(strategy = GenerationType.SEQUENCE)*/
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
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createAt;



}
