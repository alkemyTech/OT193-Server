package com.alkemy.somosmas.dtos;

public class SlidegetSlidesDTO {
    private String imageUrl;

    private Integer order_ong;

    public SlidegetSlidesDTO() {
    }

    public SlidegetSlidesDTO(String imageUrl, Integer order_ong) {
        this.imageUrl = imageUrl;
        this.order_ong = order_ong;
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
}
