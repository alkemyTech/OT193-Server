package com.alkemy.somosmas.models;



import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "slides")
@Getter
@Setter

public class Slide {
    
   /* Los mismos tendrán como campos imageUrl, text, order y organizationId 
    (ya que pertenecerán una ONG).*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String imageUrl;

    private Integer order_ong;

    private Integer organizationId;

    

    @Column(columnDefinition = "TEXT")
    private String text;

   /* @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;*/

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getOrder_ong() {
        return order_ong;
    }

    public void setOrder_ong(Integer order_ong) {
        this.order_ong = order_ong;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

   /* public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;

    
    }*/

    

   
}
