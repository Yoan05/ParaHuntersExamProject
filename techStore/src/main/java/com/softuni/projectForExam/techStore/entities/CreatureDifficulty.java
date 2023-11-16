package com.softuni.projectForExam.techStore.entities;

import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "creatureDifficulties")
public class CreatureDifficulty extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private CreatureDifficultyEnum name;
    private String description;

    public CreatureDifficultyEnum getName() {
        return name;
    }

    public void setName(CreatureDifficultyEnum name) {
        this.name = name;
        setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescription(CreatureDifficultyEnum name) {
        if (name == CreatureDifficultyEnum.EASY) {
            setDescription("Easy");
        } else if (name == CreatureDifficultyEnum.MEDIUM) {
            setDescription("Medium");
        } else if (name == CreatureDifficultyEnum.HARD) {
            setDescription("Hard");
        } else if (name == CreatureDifficultyEnum.EXPERT) {
            setDescription("Expert");
        }
    }
}
