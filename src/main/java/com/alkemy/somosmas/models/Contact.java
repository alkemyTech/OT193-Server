package com.alkemy.somosmas.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "contact")
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Contact {
    @Id
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

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDateTime deletedAt;

    private Boolean deleted = false;

    @PrePersist
    private void beforePersisting() {
        this.deletedAt= LocalDateTime.now();
    }

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
