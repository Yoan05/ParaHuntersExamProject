package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestHomeController {
    @Autowired
    MockMvc mockMvc;
    private ProductService mockProductService;


    private HomeController homeController;

    @BeforeEach
    void setUp(){
        mockProductService = Mockito.mock(ProductService.class);
        homeController = new HomeController(mockProductService);
    }

    @Test
    public void TestIfIndexReturnsIndexPage(){
        ModelAndView expected = new ModelAndView("index");

        assertEquals(expected.getView(), homeController.index().getView());
    }

    @Test
    @WithMockUser(username = "pesho@email")
    public void TestIfHomeReturnsHomePage(){
        ModelAndView expected = new ModelAndView("home");

        assertEquals(expected.getView(), homeController.home().getView());
    }
}
