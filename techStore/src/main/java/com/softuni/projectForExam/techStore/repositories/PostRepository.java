package com.softuni.projectForExam.techStore.repositories;

import com.softuni.projectForExam.techStore.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
