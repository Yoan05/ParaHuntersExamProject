package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserController {
    private UserService mockUserService;
    private UserRepository mockUserRepository;

    private UserController userController;
    private UserRegisterBindingModel testUserRegisterBindingModelAdmin;
    private UserEntity testUserP;

    @BeforeEach
    void setUp(){
        this.mockUserService = Mockito.mock(UserService.class);
        this.mockUserRepository = Mockito.mock(UserRepository.class);
        this.userController = new UserController(mockUserService, mockUserRepository);

        this.testUserRegisterBindingModelAdmin = new UserRegisterBindingModel() {{
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setConfirmPassword("1234");
            setHunterCode("1111");
        }};

        this.testUserP = new UserEntity() {{
            setId(1L);
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setHunterCode("1111");
        }};
    }

    @Test
    public void LoginReturnsProperModelAndView(){
        ModelAndView expected = new ModelAndView("login");

        assertEquals(expected.getView(), userController.login().getView());
    }
    @Test
    public void RegisterReturnsProperModelAndView(){
        ModelAndView expected = new ModelAndView("register");

        assertEquals(expected.getView(), userController.register().getView());
    }

    @Test
    public void RegisterReturnsProperModelAndViewAfterRegister(){
        ModelAndView ifTrue = new ModelAndView("redirect:/login");
        ModelAndView ifFalse = new ModelAndView("redirect:/");

        assertEquals(ifTrue.getView(), userController.register(testUserRegisterBindingModelAdmin).getView());
        assertEquals(ifFalse.getView(), userController.register(null).getView());
    }

    @Test
    @WithMockUser(username = "pesho@email.com")
    public void AllUsersReturnProperModelAndView(){

        ModelAndView expected = new ModelAndView("all-accounts");

        assertEquals(expected.getView(), userController.allUsers().getView());
    }

    @Test
    public void TestDeleteDeletesUserFromRepoAndReturnsProperModelAndView(){
        Mockito.when(mockUserRepository.findById(1L)).thenReturn(Optional.of(testUserP));

        ModelAndView ifTrue = new ModelAndView("redirect:/users/all-accounts");
        ModelAndView ifFalse = new ModelAndView("redirect:/users/all-accounts");

        assertEquals(ifTrue.getView(), userController.delete(1L).getView());
        assertEquals(ifFalse.getView(), userController.delete(200L).getView());
    }
}
