package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideGetDTO {
    
    private String imageUrl;
    private Integer order;
    private String text;
    private Long organizationId;
    
    public SlideGetDTO() {
    }

   /* public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Long getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }*/

}
