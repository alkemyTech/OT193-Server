package com.alkemy.somosmas.dtos;

public class SlideDTO {

    private String imageUrl;

    private Integer order_ong;

    private Integer organizationId;

    private String text;

    public SlideDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SlideDTO(String imageUrl, Integer order_ong, Integer organizationId) {
        this.imageUrl = imageUrl;
        this.order_ong = order_ong;
        this.organizationId = organizationId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getOrder_ong() {
        return order_ong;
    }

    public void setOrder_ong(Integer order_ong) {
        this.order_ong = order_ong;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

}
