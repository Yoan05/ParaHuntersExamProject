package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.Post;
import com.softuni.projectForExam.techStore.models.PostCreateBindingModel;
import com.softuni.projectForExam.techStore.repositories.PostRepository;
import com.softuni.projectForExam.techStore.services.PostService;
import com.softuni.projectForExam.techStore.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPostItController {
    @Autowired
    MockMvc mockMvc;

    private PostService mockPostService;
    private PostRepository mockPostRepository;
    private PostItController postItController;
    private PostCreateBindingModel testPostCreateBindingModel;
    private UserService userService;
    private Post testPost;

    @BeforeEach
    void setUp(){
        this.testPostCreateBindingModel = new PostCreateBindingModel(){{
            setDescription("Les goo");
            setImageUrl("postImgUrl");
        }};

        this.testPost = new Post(){{
            setId(1L);
            setDescription("Les goo");
            setImgUrl("postImgUrl");
        }};

        mockPostService = Mockito.mock(PostService.class);
        mockPostRepository = Mockito.mock(PostRepository.class);
        userService = Mockito.mock(UserService.class);
        postItController = new PostItController(mockPostService, mockPostRepository, userService);
    }

    @Test
    public void TestHomeReturnsPostItHomePage(){
        ModelAndView expected = new ModelAndView("post-it-home");

        assertEquals(expected.getView(), postItController.home().getView());
    }

    @Test
    public void TestCreateReturnsCreatePostPage(){
        ModelAndView expected = new ModelAndView("create-post");

        assertEquals(expected.getView(), postItController.create().getView());
    }

    @Test
    public void TestCreateReturnsHomePageAfterCreatingAPost(){
        Mockito.when(mockPostService.create(testPostCreateBindingModel)).thenReturn(true);
        ModelAndView ifFalse = new ModelAndView("redirect:/post-it/create");
        ModelAndView ifTrue = new ModelAndView("redirect:/post-it/home");

        assertEquals(ifTrue.getView(), postItController.create(testPostCreateBindingModel).getView());
        assertEquals(ifFalse.getView(), postItController.create(null).getView());
    }
}
