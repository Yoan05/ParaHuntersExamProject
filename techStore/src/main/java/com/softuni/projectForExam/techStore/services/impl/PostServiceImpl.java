package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.Post;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.PostCreateBindingModel;
import com.softuni.projectForExam.techStore.repositories.PostRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public boolean create(PostCreateBindingModel postCreateBindingModel) {
        if (!postCreateBindingModel.getImageUrl().isBlank()) {
            Post post = new Post();
            if (postCreateBindingModel.getDescription().isBlank()){
                post.setDescription("");
            } else {
                post.setDescription(postCreateBindingModel.getDescription());
            }
            post.setImgUrl(postCreateBindingModel.getImageUrl());
            post.setCreatedBy(getCurrentUser());
            post.setDate(LocalDateTime.now());
            postRepository.save(post);
            return true;
        }

        return false;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void like(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.deleteById(id);
            if (!post.getLikedBy().contains(getCurrentUser())) {
                post.getLikedBy().add(getCurrentUser());
            } else {
                post.getLikedBy().remove(getCurrentUser());
            }
            postRepository.save(post);
        }
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();

        return userRepository.findByFullName(currentUserName);
    }
}
