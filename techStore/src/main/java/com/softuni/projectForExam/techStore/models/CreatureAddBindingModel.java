package com.softuni.projectForExam.techStore.models;

import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;

public class CreatureAddBindingModel {
    private String name;
    private String description;
    private String region;
    private CreatureDifficultyEnum difficulty;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public CreatureDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(CreatureDifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
