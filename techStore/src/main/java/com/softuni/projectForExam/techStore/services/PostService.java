package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.Post;
import com.softuni.projectForExam.techStore.models.PostCreateBindingModel;

import java.util.List;

public interface PostService {
    boolean create(PostCreateBindingModel postCreateBindingModel);

    List<Post> getAllPosts();

    void like(Long id);
}
