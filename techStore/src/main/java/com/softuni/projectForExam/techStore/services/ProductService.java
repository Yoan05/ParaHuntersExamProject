package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.CreateProductBindingModel;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDisplayDTO;

public interface ProductService {
    boolean create(CreateProductBindingModel createProductBindingModel);

    ListingDisplayDTO getListingsForDisplay();

    void buy(Long id);

    String searchProduct(String name);
}
