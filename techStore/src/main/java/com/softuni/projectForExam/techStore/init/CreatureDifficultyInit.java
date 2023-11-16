package com.softuni.projectForExam.techStore.init;

import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;
import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;
import com.softuni.projectForExam.techStore.repositories.CreatureDifficultyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CreatureDifficultyInit implements CommandLineRunner {
    private final CreatureDifficultyRepository creatureDifficultyRepository;

    public CreatureDifficultyInit(CreatureDifficultyRepository creatureDifficultyRepository) {
        this.creatureDifficultyRepository = creatureDifficultyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isEmpty = creatureDifficultyRepository.findAll().isEmpty();
        if (isEmpty){
            List<CreatureDifficulty> list = new ArrayList<>();
            Arrays.stream(CreatureDifficultyEnum.values()).forEach(cd -> {
                CreatureDifficulty creatureDifficulty = new CreatureDifficulty();
                creatureDifficulty.setName(cd);
                list.add(creatureDifficulty);
            });
            creatureDifficultyRepository.saveAll(list);
        }
    }


}
