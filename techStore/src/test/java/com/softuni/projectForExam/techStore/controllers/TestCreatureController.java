package com.softuni.projectForExam.techStore.controllers;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.repositories.CreatureRepository;
import com.softuni.projectForExam.techStore.services.CreatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCreatureController {

    @Autowired
    MockMvc mockMvc;

    private CreatureService mockCreatureService;
    private CreatureRepository mockCreatureRepository;
    private CreatureAddBindingModel testCreatureAddBindingModel;
    private Creature testCreature;

    private CreatureController creatureController;


    @BeforeEach
    void setUp() {
        this.testCreatureAddBindingModel = new CreatureAddBindingModel() {{
            setName("Ghost");
            setDescription("Scary Ghost");
            setImageUrl("ghostImgUrl");
            setDifficulty(CreatureDifficultyEnum.EASY);
            setRegion("Romania");
        }};

        this.testCreature = new Creature(){{
            setName("Ghost");
            setImgUrl("ghostImgUrl");
            setDescription("Scary Ghost");
            setRegion("Romania");
            setId(1L);
        }};

        this.mockCreatureRepository = Mockito.mock(CreatureRepository.class);
        this.mockCreatureService = Mockito.mock(CreatureService.class);
        this.creatureController = new CreatureController(mockCreatureService, mockCreatureRepository);
    }

    @Test
    public void TestAddCreatureReturnsAddCreaturePage(){
        ModelAndView expected = new ModelAndView("add-creatures");

        assertEquals(expected.getView(), creatureController.addCreature().getView());
    }

    @Test
    public void TestAddCreatureReturnsProperPagesAfterAddingCreature(){
        Mockito.when(mockCreatureService.add(testCreatureAddBindingModel)).thenReturn(true);

        ModelAndView ifTrue = new ModelAndView("redirect:/creatures/all-creatures");
        ModelAndView ifFalse = new ModelAndView("redirect:/creatures/add");

        assertEquals(ifFalse.getView(), creatureController.addCreature(null).getView());
        assertEquals(ifTrue.getView(), creatureController.addCreature(testCreatureAddBindingModel).getView());
    }

    @Test
    public void TestAllCreaturesReturnsAllCreaturesPage(){
        ModelAndView expected = new ModelAndView("all-creatures");
        assertEquals(expected.getView(), creatureController.allCreatures().getView());
    }

    @Test
    public void TestViewCreatureReturnsProperCreaturePage(){
        Mockito.when(mockCreatureRepository.findById(1L)).thenReturn(Optional.of(testCreature));
        ModelAndView expected = new ModelAndView("creature-overview");

        assertEquals(expected.getView(), creatureController.viewCreature(1L).getView());
        assertNull(creatureController.viewCreature(200L));
    }

    @Test
    public void TestRemoveReturnsAllCreaturesPage(){
        ModelAndView expected = new ModelAndView("redirect:/creatures/all-creatures");
        assertEquals(expected.getView(), creatureController.remove(1L).getView());
    }
}
