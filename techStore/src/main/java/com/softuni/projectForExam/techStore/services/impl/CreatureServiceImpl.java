package com.softuni.projectForExam.techStore.services.impl;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.models.creatureDisplayDTOs.CreatureDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.CreatureDifficultyRepository;
import com.softuni.projectForExam.techStore.repositories.CreatureRepository;
import com.softuni.projectForExam.techStore.services.CreatureService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreatureServiceImpl implements CreatureService {
    private final CreatureRepository creatureRepository;
    private final CreatureDifficultyRepository creatureDifficultyRepository;

    public CreatureServiceImpl(CreatureRepository creatureRepository, CreatureDifficultyRepository creatureDifficultyRepository) {
        this.creatureRepository = creatureRepository;
        this.creatureDifficultyRepository = creatureDifficultyRepository;
    }


    @Override
    public boolean add(CreatureAddBindingModel creatureAddBindingModel) {
        if (!isNull(creatureAddBindingModel)){

            Creature creature = new Creature();
            CreatureDifficulty difficulty = creatureDifficultyRepository.findByName(creatureAddBindingModel.getDifficulty());

            creature.setName(creatureAddBindingModel.getName());
            creature.setDescription(creatureAddBindingModel.getDescription());
            creature.setDifficulty(difficulty);
            creature.setRegion(creatureAddBindingModel.getRegion());
            creature.setImgUrl(creatureAddBindingModel.getImageUrl());

            creatureRepository.save(creature);
            return true;
        }
        return false;
    }

    @Override
    public List<CreatureDisplayDTO> creaturesForDisplay() {
        List<CreatureDisplayDTO> list = new ArrayList<>();
        for (Creature creature : creatureRepository.findAll()) {
            CreatureDisplayDTO creatureDisplayDTO = new CreatureDisplayDTO(creature);
            list.add(creatureDisplayDTO);
        }
        return list;
    }

    private static boolean isNull(CreatureAddBindingModel cabm){
        if (cabm.getName().isBlank()){
            return true;
        } else if (cabm.getDescription().isBlank()){
            return true;
        } else if (cabm.getRegion().isBlank()){
            return true;
        } else if (cabm.getImageUrl().isBlank()){
            return true;
        } else {
            return false;
        }
    }
}
