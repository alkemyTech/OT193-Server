package com.alkemy.somosmas.models;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Set<User> userList;

    @Column(name = "createAt")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createAt;

}