package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.Product;
import com.softuni.projectForExam.techStore.models.CreateProductBindingModel;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.ProductRepository;
import com.softuni.projectForExam.techStore.services.ProductService;
import com.softuni.projectForExam.techStore.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductController(ProductService productService, ProductRepository productRepository, UserService userService) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @GetMapping("/listings/view-listing/{id}")
    public ModelAndView view(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("listing-overview");

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            modelAndView.addObject("viewedProduct", product);
            modelAndView.addObject("currentUser", userService.getCurrentUser());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @GetMapping("/listings/view-my-listing/{id}")
    public ModelAndView viewMine(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("my-listing-overview");

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            modelAndView.addObject("viewedProduct", product);
            return modelAndView;
        } else {
         return new ModelAndView("redirect:/home");
        }
    }

    @GetMapping("/listings/all-listings")
    public ModelAndView allListings(){
        ModelAndView modelAndView = new ModelAndView("all-listings");
        ListingDisplayDTO listingDisplayDTO = productService.getListingsForDisplay();

        modelAndView.addObject("listingDisplayDTO", listingDisplayDTO);

        return modelAndView;
    }

    @GetMapping("/listings/create")
    public ModelAndView create(){
        return new ModelAndView("create-listing");
    }

    @PostMapping("/listings/create")
    public ModelAndView create(CreateProductBindingModel createProductBindingModel){
        boolean isCreated = productService.create(createProductBindingModel);
        if (isCreated){
            return new ModelAndView("redirect:/home");
        } else{
            return new ModelAndView("redirect:/listings/create");
        }
    }

    @PostMapping("/listings/buy/{id}")
    public ModelAndView buy(@PathVariable("id") Long id){
        productService.buy(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/listings/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/listings/search")
    public ModelAndView search(String name){
        return new ModelAndView(productService.searchProduct(name));
    }
}
