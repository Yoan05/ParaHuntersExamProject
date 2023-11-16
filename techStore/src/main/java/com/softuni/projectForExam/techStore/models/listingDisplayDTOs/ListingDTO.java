package com.softuni.projectForExam.techStore.models.listingDisplayDTOs;

import com.softuni.projectForExam.techStore.entities.Product;
import com.softuni.projectForExam.techStore.entities.ProductType;
import com.softuni.projectForExam.techStore.entities.UserEntity;

public class ListingDTO extends BoughtProductsDTO {

    private ProductType type;
    private String description;
    private UserEntity seller;

    public ListingDTO(){

    }
    public ListingDTO(Product product){
        super(product);
        type = product.getType();
        description = product.getDescription();
        seller = product.getCreatedBy();
    }


    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }
}
