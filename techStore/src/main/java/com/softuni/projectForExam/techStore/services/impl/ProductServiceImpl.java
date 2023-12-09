package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.Product;
import com.softuni.projectForExam.techStore.entities.ProductType;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.BoughtProductsDTO;
import com.softuni.projectForExam.techStore.models.CreateProductBindingModel;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDTO;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.ProductRepository;
import com.softuni.projectForExam.techStore.repositories.ProductTypeRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.ProductService;
import com.softuni.projectForExam.techStore.services.UserService;
import com.softuni.projectForExam.techStore.services.exception.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    private final UserService userService;

    public ProductServiceImpl(ProductRepository productRepository, ProductTypeRepository productTypeRepository, UserService userService) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
        this.userService = userService;
    }

    @Override
    public boolean create(CreateProductBindingModel createProductBindingModel) {
        if (isNull(createProductBindingModel)) {
            return false;
        }
        ProductType extractedType = productTypeRepository.getByType(createProductBindingModel.getType());
        Product product = new Product();

        product.setName(createProductBindingModel.getName());
        product.setPrice(createProductBindingModel.getPrice());
        product.setType(extractedType);
        product.setDescription(createProductBindingModel.getDescription());
        product.setCreatedBy(userService.getCurrentUser());
        product.setImageUrl(createProductBindingModel.getImageUrl());
        product.setBought(false);

        productRepository.save(product);
        return true;
    }

    @Override
    public ListingDisplayDTO getListingsForDisplay() {
        List<Product> products = productRepository.findAll();

        List<BoughtProductsDTO> boughtProducts = new ArrayList<>();
        List<ListingDTO> myListings = new ArrayList<>();
        List<ListingDTO> listings = new ArrayList<>();
        List<ListingDTO> allListings = new ArrayList<>();

        for (Product product : products) {
            allListings.add(new ListingDTO(product));

            if (product.getCreatedBy().getFullName().equals(userService.getCurrentUser().getFullName()) && product.isBought()) {
                boughtProducts.add(new BoughtProductsDTO(product));
            } else if (product.getCreatedBy().getFullName().equals(userService.getCurrentUser().getFullName()) && !product.isBought()) {
                myListings.add(new ListingDTO(product));
            } else if (!product.getCreatedBy().getFullName().equals(userService.getCurrentUser().getFullName()) && !product.isBought()) {
                listings.add(new ListingDTO(product));
            }
        }

        return new ListingDisplayDTO(boughtProducts, myListings, listings, allListings);
    }

    @Override
    public void buy(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        productRepository.deleteById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setCreatedBy(userService.getCurrentUser());
            product.setBought(true);
            productRepository.save(product);
        }
    }

    @Override
    public String searchProduct(String name) throws ObjectNotFoundException{
        if (name == null){
            throw new ObjectNotFoundException("Not found");
        }
        List<String> allProductNames = productRepository.findAll().stream().map(Product::getName).toList();

        for (String pName : allProductNames) {
            if (pName.equalsIgnoreCase(name)) {
                    Product product = productRepository.findByName(name);
                    if (!product.isBought()) {
                        return "redirect:/listings/view-listing/" + product.getId();
                    }
            }
        }
        throw new ObjectNotFoundException("Not found");
    }

    private static boolean isNull(CreateProductBindingModel cpbm){
        if (cpbm.getName().isBlank()){
            return true;
        } else if (cpbm.getDescription().isBlank()){
            return true;
        } else if (cpbm.getPrice() == null){
            return true;
        } else if (cpbm.getImageUrl().isBlank()){
            return true;
        } else {
            return false;
        }
    }
}
