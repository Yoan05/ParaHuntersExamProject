package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.impl.ParaHuntersUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestParaHuntersUserDetailsService {

    private UserRepository mockUserRepository;
    private UserEntity testUser;
    private UserDetails testUserDetails;
    private SimpleGrantedAuthority adminRole;
    private SimpleGrantedAuthority userRole;
    private RoleEntity admin;
    private RoleEntity user;

    @BeforeEach
    void setUp(){
        this.mockUserRepository = Mockito.mock(UserRepository.class);

        this.admin = new RoleEntity(){{
            setName(RolesEnum.ADMIN);
        }};

        this.user = new RoleEntity(){{
            setName(RolesEnum.USER);
        }};

        testUser = new UserEntity(){{
            setId(3);
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setHunterCode("1111");
            setRoles(List.of(admin, user));
        }};

        adminRole = new SimpleGrantedAuthority("ROLE_ADMIN");

        userRole = new SimpleGrantedAuthority("ROLE_USER");


        testUserDetails = new User("Pesho Peshev", "1234", List.of(adminRole, userRole));
    }

    @Test
    public void ParaHuntersUserDetailsServiceLoadsCorrectUserByUsername(){
        Mockito.when(mockUserRepository.findByEmail("pesho@email")).thenReturn(Optional.of(testUser));

        ParaHuntersUserDetailsService paraHuntersUserDetailsService = new ParaHuntersUserDetailsService(mockUserRepository);

        UserDetails expected = testUserDetails;

        UserDetails actual = paraHuntersUserDetailsService.loadUserByUsername("pesho@email");

        assertEquals(expected, actual);
    }
}
