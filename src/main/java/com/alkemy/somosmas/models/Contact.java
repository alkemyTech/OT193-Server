package com.alkemy.somosmas.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "contact")
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE deletedAt is null")//modificar
@Where(clause = "deleted = false")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact", nullable = false)
    private Long id;

    @Column(name = "name_contact")
    private String name;

    @Column(name = "phone_contact")
    private Integer phone;

    @Column(name = "email_contact")
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt=null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

}
