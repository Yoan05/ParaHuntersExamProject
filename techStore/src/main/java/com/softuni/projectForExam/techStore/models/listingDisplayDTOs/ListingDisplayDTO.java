package com.softuni.projectForExam.techStore.models.listingDisplayDTOs;

import java.util.List;

public class ListingDisplayDTO {
    private List<BoughtProductsDTO> boughtProducts;
    private List<ListingDTO> myListings;
    private List<ListingDTO> listings;
    private List<ListingDTO> allListings;

    public ListingDisplayDTO(){

    }
    public ListingDisplayDTO(List<BoughtProductsDTO> boughtProducts,List<ListingDTO> myListings,List<ListingDTO> listings, List<ListingDTO> allListings){
        this.boughtProducts = boughtProducts;
        this.myListings = myListings;
        this.listings = listings;
        this.allListings = allListings;
    }

    public List<BoughtProductsDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<BoughtProductsDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public List<ListingDTO> getListings() {
        return listings;
    }

    public void setListings(List<ListingDTO> listings) {
        this.listings = listings;
    }

    public List<ListingDTO> getMyListings() {
        return myListings;
    }

    public void setMyListings(List<ListingDTO> myListings) {
        this.myListings = myListings;
    }

    public List<ListingDTO> getAllListings() {
        return allListings;
    }

    public void setAllListings(List<ListingDTO> allListings) {
        this.allListings = allListings;
    }
}
