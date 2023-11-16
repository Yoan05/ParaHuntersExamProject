package com.softuni.projectForExam.techStore.config;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class TestAuthenticationConfiguration {

    @Bean
    @Primary
    public UserDetailsService testUserDetailsService(){
        UserEntity basicUser = new UserEntity();
        basicUser.setFullName("Basic User");
        basicUser.setEmail("user@email.com");
        basicUser.setPassword("1234");
        basicUser.setId(1L);
        basicUser.setHunterCode("0000");

        RoleEntity adminRole = new RoleEntity();
        adminRole.setName(RolesEnum.ADMIN);

        RoleEntity userRole = new RoleEntity();
        adminRole.setName(RolesEnum.USER);

        basicUser.setRoles(List.of(userRole));

        UserEntity adminUser = new UserEntity();
        adminUser.setFullName("Admin User");
        adminUser.setEmail("admin@email.com");
        adminUser.setPassword("1234");
        adminUser.setId(2L);
        adminUser.setHunterCode("1111");

        adminUser.setRoles(List.of(adminRole, userRole));

        List<UserEntity> list = List.of(adminUser, basicUser);

        return new InMemoryUserDetailsManager((UserDetails) list);
    }
}
