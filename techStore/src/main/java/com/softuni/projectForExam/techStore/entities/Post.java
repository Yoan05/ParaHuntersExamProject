package com.softuni.projectForExam.techStore.entities;

import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{
    @NotNull
    private String description;
    @ManyToOne
    private UserEntity createdBy;
    @NotNull
    private String imgUrl;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserEntity> likedBy;
    private LocalDateTime date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<UserEntity> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<UserEntity> likedBy) {
        this.likedBy = likedBy;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
