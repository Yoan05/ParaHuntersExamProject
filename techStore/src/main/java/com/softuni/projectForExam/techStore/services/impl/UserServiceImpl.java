package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.models.userDisplayDTOs.UserDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.RoleRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.UserService;
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

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel != null){

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userRegisterBindingModel.getEmail());
            userEntity.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
            userEntity.setHunterCode(userRegisterBindingModel.getHunterCode());
            userEntity.setFullName(userRegisterBindingModel.getFullName());
            if (userRegisterBindingModel.getHunterCode().equals("1111")){
                userEntity.setRoles(loadAdminRoles());
            } else if (userRegisterBindingModel.getHunterCode().equals("0000")){
                userEntity.setRoles(loadUserRoles());
            }

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

    private List<RoleEntity> loadAdminRoles(){
        RoleEntity roleUser = roleRepository.findByName(RolesEnum.USER);
        RoleEntity roleAdmin = roleRepository.findByName(RolesEnum.ADMIN);
        List<RoleEntity> admin = new ArrayList<>();

        admin.add(roleAdmin);
        admin.add(roleUser);

        return admin;
    }

    private List<RoleEntity> loadUserRoles(){
        RoleEntity roleUser = roleRepository.findByName(RolesEnum.USER);
        List<RoleEntity> user = new ArrayList<>();

        user.add(roleUser);

        return user;
    }


}
