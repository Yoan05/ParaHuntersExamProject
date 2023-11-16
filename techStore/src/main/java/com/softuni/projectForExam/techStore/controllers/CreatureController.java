package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.models.creatureDisplayDTOs.CreatureDisplayDTO;
import com.softuni.projectForExam.techStore.repositories.CreatureRepository;
import com.softuni.projectForExam.techStore.services.CreatureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class CreatureController {
    private final CreatureService creatureService;
    private final CreatureRepository creatureRepository;

    public CreatureController(CreatureService creatureService, CreatureRepository creatureRepository) {
        this.creatureService = creatureService;
        this.creatureRepository = creatureRepository;
    }

    @GetMapping("/creatures/add")
    public ModelAndView addCreature() {
        return new ModelAndView("add-creatures");
    }

    @PostMapping("/creatures/add")
    public ModelAndView addCreature(CreatureAddBindingModel creatureAddBindingModel) {
        boolean isAdded = creatureService.add(creatureAddBindingModel);
        if (isAdded) {
            return new ModelAndView("redirect:/creatures/all-creatures");
        } else {
            return new ModelAndView("redirect:/creatures/add");
        }
    }

    @GetMapping("/creatures/all-creatures")
    public ModelAndView allCreatures() {
        ModelAndView model = new ModelAndView("all-creatures");
        model.addObject("allCreatures", creatureService.creaturesForDisplay());

        return model;
    }

    @GetMapping("/creatures/view/{id}")
    public ModelAndView viewCreature(@PathVariable("id") Long id) {
        Optional<Creature> creatureOptional = creatureRepository.findById(id);
        if (creatureOptional.isPresent()){
            Creature creature = creatureOptional.get();
            ModelAndView model = new ModelAndView("creature-overview");
            model.addObject("viewedCreature", creature);
            return model;
        }
        return null;
    }

    @PostMapping("/creatures/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        creatureRepository.deleteById(id);
        return new ModelAndView("redirect:/creatures/all-creatures");
    }
}
