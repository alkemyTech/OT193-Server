package com.alkemy.somosmas.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "activity")
@SQLDelete(sql = "UPDATE activity SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Activity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_activity")
    private String nameActivity;

    @Column(name = "content_activity")
    private String contentActivity;

    @Column(name = "image_activity")
    private String imageActivity;

    @Column(name = "create_at")
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private LocalDate createAt;

    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public String getContentActivity() {
        return contentActivity;
    }

    public void setContentActivity(String contentActivity) {
        this.contentActivity = contentActivity;
    }

    public String getImageActivity() {
        return imageActivity;
    }

    public void setImageActivity(String imageActivity) {
        this.imageActivity = imageActivity;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
