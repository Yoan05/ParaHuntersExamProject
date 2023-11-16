package com.softuni.projectForExam.techStore.models.listingDisplayDTOs;

import com.softuni.projectForExam.techStore.entities.Product;

import java.math.BigDecimal;

public class BoughtProductsDTO {
    private String name;
    private BigDecimal price;
    private long id;
    private String imageUrl;

    public BoughtProductsDTO(){

    }
    public BoughtProductsDTO(Product product){
        name = product.getName();
        price = product.getPrice();
        imageUrl= product.getImageUrl();
        id = product.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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
}
