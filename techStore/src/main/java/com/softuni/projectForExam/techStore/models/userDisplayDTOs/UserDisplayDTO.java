package com.softuni.projectForExam.techStore.models.userDisplayDTOs;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;

import java.util.List;

public class UserDisplayDTO {
    private String fullName;
    private String email;
    private Long id;
    private List<RoleEntity> roles;
    public UserDisplayDTO(){}
    public UserDisplayDTO(UserEntity user){
        fullName = user.getFullName();
        email = user.getEmail();
        id = user.getId();
        roles = user.getRoles();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
