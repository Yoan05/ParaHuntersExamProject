package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDisplayDTO;
import com.softuni.projectForExam.techStore.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        ListingDisplayDTO listingDisplayDTO = productService.getListingsForDisplay();

        modelAndView.addObject("listingDisplayDTO", listingDisplayDTO);

        return modelAndView;
    }
}
