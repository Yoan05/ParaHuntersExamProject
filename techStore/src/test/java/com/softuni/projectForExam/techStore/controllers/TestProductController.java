package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.Product;
import com.softuni.projectForExam.techStore.models.CreateProductBindingModel;
import com.softuni.projectForExam.techStore.repositories.ProductRepository;
import com.softuni.projectForExam.techStore.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProductController {
    @Autowired
    MockMvc mockMvc;
    private ProductService mockProductService;
    private ProductRepository mockProductRepository;
    private CreateProductBindingModel testCreateProductBindingModel;
    private Product testProduct;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockProductService = Mockito.mock(ProductService.class);
        mockProductRepository = Mockito.mock(ProductRepository.class);
        productController = new ProductController(mockProductService, mockProductRepository);
        this.testCreateProductBindingModel = new CreateProductBindingModel() {{
            setName("Pistol");
            setDescription("Big pistol");
            setPrice(BigDecimal.valueOf(15));
            setImageUrl("PistolImgUrl");
        }};

        this.testProduct = new Product() {{
            setId(1L);
            setName("Pistol");
            setDescription("Big pistol");
            setPrice(BigDecimal.valueOf(15));
            setBought(false);
            setImageUrl("pistolImgUrl");
        }};
    }

    @Test
    public void TestViewReturnsCorrectListingPage() {
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        ModelAndView ifPresent = new ModelAndView("listing-overview");
        ModelAndView ifNotPresent = new ModelAndView("redirect:/home");

        assertEquals(ifPresent.getView(), productController.view(1L).getView());
        assertEquals(ifNotPresent.getView(), productController.view(2L).getView());
    }

    @Test
    public void TestViewMineReturnsCorrectListingPage() {
        Mockito.when(mockProductRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        ModelAndView ifPresent = new ModelAndView("listing-overview");
        ModelAndView ifNotPresent = new ModelAndView("redirect:/home");

        assertEquals(ifPresent.getView(), productController.viewMine(1L).getView());
        assertEquals(ifNotPresent.getView(), productController.viewMine(2L).getView());
    }

    @Test
    public void TestAllListingsReturnsAllListingsPage() {
        ModelAndView expected = new ModelAndView("all-listings");

        assertEquals(expected.getView(), productController.allListings().getView());
    }

    @Test
    public void TestCreateListingReturnsAddListingPage() {
        ModelAndView expected = new ModelAndView("create-listing");

        assertEquals(expected.getView(), productController.create().getView());
    }

    @Test
    public void TestCreateListingReturnsHomePageAfterCreatingNewListing() {
        Mockito.when(mockProductService.create(testCreateProductBindingModel)).thenReturn(true);
        ModelAndView ifTrue = new ModelAndView("redirect:/home");
        ModelAndView ifFalse = new ModelAndView("redirect:/listings/create");

        assertEquals(ifTrue.getView(), productController.create(testCreateProductBindingModel).getView());
        assertEquals(ifFalse.getView(), productController.create(null).getView());
    }

    @Test
    public void TestBuyReturnsHomePageAfterSuccessfulOperation() {
        ModelAndView expected = new ModelAndView("redirect:/home");
        assertEquals(expected.getView(), productController.buy(1L).getView());
    }

    @Test
    public void TestDeleteListingReturnsHomePageAfterSuccessfulOperation() {
        ModelAndView expected = new ModelAndView("redirect:/home");
        assertEquals(expected.getView(), productController.delete(1L).getView());
    }
}