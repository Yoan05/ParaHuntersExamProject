package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class  ParaHuntersUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ParaHuntersUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(ParaHuntersUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + "not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getFullName())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(ParaHuntersUserDetailsService::map).toList())
                .build();
    }

    private static GrantedAuthority map(RoleEntity roleEntity){
        return new SimpleGrantedAuthority("ROLE_" + roleEntity.getName().name());
    }
}
