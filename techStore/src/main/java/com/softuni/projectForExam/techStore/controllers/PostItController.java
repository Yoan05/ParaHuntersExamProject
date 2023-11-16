package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.models.PostCreateBindingModel;
import com.softuni.projectForExam.techStore.repositories.PostRepository;
import com.softuni.projectForExam.techStore.services.PostService;
import com.softuni.projectForExam.techStore.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostItController {
    private final PostService postService;
    private final PostRepository postRepository;
    private final UserService userService;

    public PostItController(PostService postService, PostRepository postRepository, UserService userService) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userService = userService;
    }


    @GetMapping("/post-it/home")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("post-it-home");
        model.addObject("allPosts", postService.getAllPosts());
        model.addObject("currentUser", userService.getCurrentUser());
        return model;
    }
    @GetMapping("/post-it/create")
    public ModelAndView create(){
        return new ModelAndView("create-post");
    }

    @PostMapping("/post-it/create")
    public ModelAndView create(PostCreateBindingModel postCreateBindingModel){
        boolean isCreated = postService.create(postCreateBindingModel);

        if (isCreated){
            return new ModelAndView("redirect:/post-it/home");
        }
        return new ModelAndView("redirect:/post-it/create");
    }
    @PostMapping("/post-it/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        postRepository.deleteById(id);
        return new ModelAndView("redirect:/post-it/home");
    }
    @PostMapping("/post-it/like/{id}")
    public ModelAndView like(@PathVariable("id") Long id){
        postService.like(id);
        return new ModelAndView("redirect:/post-it/home");
    }
}
