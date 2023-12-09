package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.Product;
import com.softuni.projectForExam.techStore.entities.ProductType;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.ProductTypeEnum;
import com.softuni.projectForExam.techStore.models.CreateProductBindingModel;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.models.listingDisplayDTOs.ListingDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.ProductRepository;
import com.softuni.projectForExam.techStore.repositories.ProductTypeRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.exception.ObjectNotFoundException;
import com.softuni.projectForExam.techStore.services.impl.ProductServiceImpl;
import org.apache.tomcat.jni.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.web.servlet.oauth2.login.UserInfoEndpointDsl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProductService {

    @Autowired
    MockMvc mockMvc;
    private ProductRepository mockProductRepository;
    private ProductTypeRepository mockProductTypeRepository;
    private UserService mockUserService;
    private UserEntity testUserP;
    private Product testProduct;
    private Product testProduct2;

    private ProductType testType;
    private CreateProductBindingModel testCreateProductBindingModel;
    private CreateProductBindingModel blackProduct;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        this.mockProductRepository = Mockito.mock(ProductRepository.class);
        this.mockProductTypeRepository = Mockito.mock(ProductTypeRepository.class);
        this.mockUserService = Mockito.mock(UserService.class);

        this.testType = new ProductType() {{
            setId(4);
            setType(ProductTypeEnum.RANGED_WEAPON);
            setDescription(ProductTypeEnum.RANGED_WEAPON);
        }};

        this.testUserP = new UserEntity() {{
            setId(3);
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setHunterCode("1111");
        }};

        this.testProduct = new Product() {{
            setId(2);
            setName("Pistol");
            setDescription("Big pistol");
            setCreatedBy(testUserP);
            setPrice(BigDecimal.valueOf(15));
            setBought(false);
            setImageUrl("pistolImgUrl");
            setType(testType);
        }};

        this.testProduct2 = new Product() {{
            setId(1);
            setName("Pump");
            setDescription("Big pump");
            setCreatedBy(testUserP);
            setPrice(BigDecimal.valueOf(35));
            setBought(false);
            setImageUrl("PumpImgUrl");
            setType(testType);
        }};

        this.testCreateProductBindingModel = new CreateProductBindingModel() {{
            setName("Pistol");
            setDescription("Big pistol");
            setPrice(BigDecimal.valueOf(15));
            setImageUrl("PistolImgUrl");
        }};

        this.blackProduct = new CreateProductBindingModel(){{
            setName("");
            setDescription("");
            setPrice(null);
            setImageUrl("");
        }};

        this.productService = new ProductServiceImpl(mockProductRepository, mockProductTypeRepository, mockUserService);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestProductServiceShouldCreateAProductSuccessfully() {
        Mockito.when(mockProductRepository.findByName("Pistol")).thenReturn(testProduct);

        assertFalse(productService.create(blackProduct));

        Product expected = testProduct;

        Assertions.assertTrue(productService.create(testCreateProductBindingModel));

        productService.create(testCreateProductBindingModel);

        Product actual = mockProductRepository.findByName("Pistol");

        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestProductServiceShouldLetUsersBuyProductsFlawlessly(){
        Mockito.when(mockProductRepository.findById(2L)).thenReturn(Optional.of(testProduct));

        productService.buy(testProduct.getId());

        Assertions.assertTrue(testProduct.isBought());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestGetProductsForDisplayReturnsProperLists(){
        Mockito.when(mockProductRepository.findAll()).thenReturn(List.of(testProduct, testProduct2));

        Mockito.when(mockUserService.getCurrentUser()).thenReturn(testUserP);

        ListingDisplayDTO actual = productService.getListingsForDisplay();

        assertFalse(actual.getAllListings().isEmpty());
    }

    @Test
    public void TestSearchBarThrowsWhenThereIsNoSuchProduct(){
        assertThrows(ObjectNotFoundException.class, () -> {
            productService.searchProduct("banana");
        });
    }
}
