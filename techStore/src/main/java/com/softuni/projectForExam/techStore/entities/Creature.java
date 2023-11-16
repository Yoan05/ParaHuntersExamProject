package com.softuni.projectForExam.techStore.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Creature extends BaseEntity{
    @NotNull
    private String name;
    @NotNull
    private String description;
    @OneToOne
    private CreatureDifficulty difficulty;
    @NotNull
    private String region;
    @NotNull
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

    public CreatureDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(CreatureDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImgUrl() {
        return imageUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }
}
