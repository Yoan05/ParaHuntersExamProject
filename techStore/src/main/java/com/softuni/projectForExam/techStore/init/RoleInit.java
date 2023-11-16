package com.softuni.projectForExam.techStore.init;

import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;
import com.softuni.projectForExam.techStore.entities.RoleEntity;
import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;
import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;
import com.softuni.projectForExam.techStore.repositories.CreatureDifficultyRepository;
import com.softuni.projectForExam.techStore.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RoleInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInit(RoleRepository creatureDifficultyRepository) {
        this.roleRepository = creatureDifficultyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isEmpty = roleRepository.findAll().isEmpty();
        if (isEmpty) {
            List<RoleEntity> list = new ArrayList<>();
            Arrays.stream(RolesEnum.values()).forEach(rn -> {
                RoleEntity role = new RoleEntity();
                role.setName(rn);
                list.add(role);
            });
            roleRepository.saveAll(list);
        }
    }
}

