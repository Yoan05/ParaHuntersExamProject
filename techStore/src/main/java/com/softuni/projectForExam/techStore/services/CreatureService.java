package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.models.creatureDisplayDTOs.CreatureDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.CreatureRepository;

import java.util.List;

public interface CreatureService {

    boolean add(CreatureAddBindingModel creatureAddBindingModel);

    List<CreatureDisplayDTO> creaturesForDisplay();
}
