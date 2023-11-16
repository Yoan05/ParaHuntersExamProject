package com.softuni.projectForExam.techStore.repositories;

import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;
import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureDifficultyRepository extends JpaRepository<CreatureDifficulty, Long> {
    CreatureDifficulty findByName(CreatureDifficultyEnum difficulty);
}
