package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.UserEntity;
import com.softuni.projectForExam.techStore.models.UserRegisterBindingModel;
import com.softuni.projectForExam.techStore.models.userDisplayDTOs.UserDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.RoleRepository;
import com.softuni.projectForExam.techStore.repositories.UserRepository;
import com.softuni.projectForExam.techStore.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserService {

    @Autowired
    private MockMvc mockMvc;

    private UserEntity testUserP;
    private UserEntity testUserG;
    private List<UserEntity> users;
    private UserRepository mockedUserRepository;
    private RoleRepository mockedRoleRepository;
    private PasswordEncoder passwordEncoder;
    private UserRegisterBindingModel testUserRegisterBindingModelAdmin;
    private UserRegisterBindingModel testUserRegisterBindingModelUser;
    private UserRegisterBindingModel nullTestUser;
    private UserService userService;

    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        this.testUserP = new UserEntity() {{
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setHunterCode("1111");
        }};
        this.testUserG = new UserEntity() {{
            setFullName("Gosho Goshev");
            setEmail("gosho@email");
            setPassword("1234");
            setHunterCode("0000");
        }};
        this.testUserRegisterBindingModelUser = new UserRegisterBindingModel() {{
            setFullName("Gosho Goshev");
            setEmail("gosho@email");
            setPassword("1234");
            setConfirmPassword("1234");
            setHunterCode("12345566");
        }};

        this.testUserRegisterBindingModelAdmin = new UserRegisterBindingModel() {{
            setFullName("Pesho Peshev");
            setEmail("pesho@email");
            setPassword("1234");
            setConfirmPassword("1234");
            setHunterCode("1111");
        }};
        this.nullTestUser = null;

        this.mockedUserRepository = Mockito.mock(UserRepository.class);

        this.mockedRoleRepository = Mockito.mock(RoleRepository.class);

        this.passwordEncoder = Mockito.mock(PasswordEncoder.class);

        this.userService = new UserServiceImpl(this.mockedUserRepository, this.passwordEncoder, this.mockedRoleRepository, mapper);

        this.mapper = new ModelMapper();

        this.users = new ArrayList<>();

        this.users.add(this.testUserG);
        this.users.add(this.testUserP);
    }

    @Test
    public void UserServiceShouldRegisterUserCorrectly() {
        Mockito.when(this.mockedUserRepository
                        .findByFullName("Gosho Goshev"))
                .thenReturn(this.testUserG);

        Assertions.assertTrue(userService.register(this.testUserRegisterBindingModelUser));
        userService.register(this.testUserRegisterBindingModelUser);

        UserEntity expectedUser = testUserG;
        UserEntity actualUser = mockedUserRepository.findByFullName("Gosho Goshev");

        Assertions.assertEquals(expectedUser.getFullName(), actualUser.getFullName());
    }

    @Test
    public void UserServiceShouldReturnCorrectUsersForDisplay() {
        Mockito.when(this.mockedUserRepository
                        .findAll())
                .thenReturn(this.users);

        List<UserDisplayDTO> expected = new ArrayList<>();
        expected.add(new UserDisplayDTO(testUserG));
        expected.add(new UserDisplayDTO(testUserP));

        List<UserDisplayDTO> actual = userService.usersForDisplay();

        Assertions.assertEquals(expected.get(0).getFullName(), actual.get(0).getFullName());
    }


}
