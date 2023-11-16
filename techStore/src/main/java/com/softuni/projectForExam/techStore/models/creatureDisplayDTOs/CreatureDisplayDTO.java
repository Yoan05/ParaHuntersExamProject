package com.softuni.projectForExam.techStore.models.creatureDisplayDTOs;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;


public class CreatureDisplayDTO {
    private String name;
    private String description;
    private CreatureDifficulty difficulty;
    private String region;
    private String imageUrl;
    private Long id;

    public CreatureDisplayDTO(){
    }
    public CreatureDisplayDTO(Creature creature){
        setName(creature.getName());
        setDescription(creature.getDescription());
        setCreatureDifficulty(creature.getDifficulty());
        setRegion(creature.getRegion());
        setImageUrl(creature.getImageUrl());
        setId(creature.getId());
    }

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

    public CreatureDifficulty getCreatureDifficulty() {
        return difficulty;
    }

    public void setCreatureDifficulty(CreatureDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CreatureDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(CreatureDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
