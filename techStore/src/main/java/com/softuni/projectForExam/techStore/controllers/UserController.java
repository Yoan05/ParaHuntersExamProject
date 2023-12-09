package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.models.userDisplayDTOs.UserDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.UserService;
import org.hibernate.TransactionException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterBindingModel userRegisterBindingModel) {
        boolean isRegistered = userService.register(userRegisterBindingModel);
        if (isRegistered) {
            return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("redirect:/register");
        }
    }

    @GetMapping("/users/all-accounts")
    public ModelAndView allUsers() {
        ModelAndView model = new ModelAndView("all-accounts");
        List<UserDisplayDTO> list = userService.usersForDisplay();
        model.addObject("allUsers", list);
        model.addObject("currentUser", userService.getCurrentUser());
        model.addObject("adminValue", RolesEnum.ADMIN);
        return model;
    }

    @PostMapping("/users/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return new ModelAndView("redirect:/users/all-accounts");
        }
        return new ModelAndView("redirect:/users/all-accounts");
    }

    @GetMapping("/users/my-account")
    public ModelAndView myAccount(){
        ModelAndView model = new ModelAndView("my-account");

        model.addObject("user", userService.getCurrentUser());

        return model;
    }

    @RequestMapping("/login-error")
    public ModelAndView loginError(){
        return new ModelAndView("error/login-error");
    }
}
