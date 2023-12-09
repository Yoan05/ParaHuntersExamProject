package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.Post;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.PostCreateBindingModel;
import com.softuni.projectForExam.techStore.repositories.PostRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPostService {

    @Autowired
    MockMvc mockMvc;

    private PostRepository mockPostRepository;
    private UserRepository mockUserRepository;
    private Post testPost;
    private PostCreateBindingModel postCreateBindingModel;
    private UserEntity testUserP;

    private PostCreateBindingModel blackPost;

    @BeforeEach
    void setUp(){
        testPost = new Post(){{
            setId(1L);
            setDescription("Les goo");
            setImgUrl("postImgUrl");
            setCreatedBy(testUserP);
        }};

        this.testUserP = new UserEntity() {{
            setId(3);
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setHunterCode("1111");
        }};

        this.postCreateBindingModel = new PostCreateBindingModel(){{
            setDescription("Les goo");
            setImageUrl("postImgUrl");
        }};

        this.blackPost = new PostCreateBindingModel(){{
           setDescription("");
           setImageUrl("");
        }};

        mockPostRepository = Mockito.mock(PostRepository.class);
        mockUserRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestPostServiceCreatesPostSuccessfully(){
        Mockito.when(mockPostRepository.findById(1L)).thenReturn(Optional.of(testPost));
        PostService postService = new PostServiceImpl(mockPostRepository, mockUserRepository);

        assertTrue(postService.create(postCreateBindingModel));
        assertFalse(postService.create(blackPost));

        Post expected = testPost;

        if (mockPostRepository.findById(1L).isPresent()){
            Post actual = mockPostRepository.findById(1L).get();

            assertEquals(expected, actual);
        }
    }

    @Test
    public void TestPostServiceGivesAllPosts(){
        Mockito.when(mockPostRepository.findAll()).thenReturn(List.of(testPost));
        PostService postService = new PostServiceImpl(mockPostRepository, mockUserRepository);

        assertFalse(postService.getAllPosts().isEmpty());
    }
}