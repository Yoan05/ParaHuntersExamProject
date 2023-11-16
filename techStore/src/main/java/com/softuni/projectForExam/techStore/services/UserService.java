package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.models.userDisplayDTOs.UserDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

    List<UserDisplayDTO> usersForDisplay();

    UserEntity getCurrentUser();
}
