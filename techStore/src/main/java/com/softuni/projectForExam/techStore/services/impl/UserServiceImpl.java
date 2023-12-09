package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.models.userDisplayDTOs.UserDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.RoleRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (!isNull(userRegisterBindingModel)){

            UserEntity userEntity = new UserEntity();

            mapper.map(userRegisterBindingModel, userEntity);

            userEntity.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

            userEntity.setRoles(rolesSetUp(userRegisterBindingModel.getHunterCode()));

            userRepository.save(userEntity);
            return true;
        }

        return false;
    }

    @Override
    public List<UserDisplayDTO> usersForDisplay() {
        List<UserDisplayDTO> list = new ArrayList<>();

        for (UserEntity user : userRepository.findAll()) {
            UserDisplayDTO dto = new UserDisplayDTO(user);
            list.add(dto);
        }

        return list;
    }

    @Override
    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return userRepository.findByFullName(currentUserName);
    }

    private List<RoleEntity> rolesSetUp(String code){
        RoleEntity roleUser = roleRepository.findByName(RolesEnum.USER);
        RoleEntity roleAdmin = roleRepository.findByName(RolesEnum.ADMIN);
        List<RoleEntity> roles = new ArrayList<>();
        if (code.equals("1111")){
            roles.add(roleAdmin);
            roles.add(roleUser);
        } else {
            roles.add(roleUser);
        }
        return roles;
    }

    private static boolean isNull(UserRegisterBindingModel urbm){
        if (urbm.getFullName().isBlank()){
            return true;
        } else if (urbm.getEmail().isBlank()){
            return true;
        } else if (urbm.getHunterCode().isBlank()){
            return true;
        } else if (urbm.getPassword().isBlank()){
            return true;
        } else {
            return false;
        }
    }
}
