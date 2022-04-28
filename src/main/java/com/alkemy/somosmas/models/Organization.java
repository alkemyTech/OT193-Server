package com.alkemy.somosmas.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

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
    private String name;
    private String image;
    private String address;
    private int phone;
    private String email;
    /* REVISAR tipo TEXT*/
    private String welcomeText;
    /* REVISAR tipo TEXT*/
    private String aboutUsText;


    private boolean deleted=Boolean.FALSE;



}
