package com.softuni.projectForExam.techStore.services;

import com.softuni.projectForExam.techStore.entities.Creature;
import com.softuni.projectForExam.techStore.entities.CreatureDifficulty;
import com.softuni.projectForExam.techStore.entities.enums.CreatureDifficultyEnum;
import com.softuni.projectForExam.techStore.models.CreatureAddBindingModel;
import com.softuni.projectForExam.techStore.repositories.CreatureDifficultyRepository;
import com.softuni.projectForExam.techStore.repositories.CreatureRepository;
import com.softuni.projectForExam.techStore.services.impl.CreatureServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCreatureService {

    @Autowired
    MockMvc mockMvc;

    private CreatureDifficulty creatureDifficulty;
    private Creature testCreature;
    private CreatureAddBindingModel creatureAddBindingModel;
    private CreatureRepository mockCreatureRepository;
    private CreatureDifficultyRepository mockCreatureDifficultyRepository;
    private CreatureAddBindingModel blackModel;

    @BeforeEach
    void setUp(){
        this.creatureAddBindingModel = new CreatureAddBindingModel(){{
            setName("Ghost");
            setDescription("Scary Ghost");
            setImageUrl("ghostImgUrl");
            setDifficulty(CreatureDifficultyEnum.EASY);
            setRegion("Romania");
        }};
        this.creatureDifficulty = new CreatureDifficulty(){{
            setName(CreatureDifficultyEnum.EASY);
        }};
        this.testCreature = new Creature(){{
           setName("Ghost");
           setImgUrl("ghostImgUrl");
           setDescription("Scary Ghost");
           setRegion("Romania");
           setId(1);
        }};
        this.blackModel = new CreatureAddBindingModel(){{
            setName("");
            setDifficulty(CreatureDifficultyEnum.EASY);
            setDescription("");
            setRegion("");
            setImageUrl("");
        }};
        this.mockCreatureRepository = Mockito.mock(CreatureRepository.class);
        this.mockCreatureDifficultyRepository = Mockito.mock(CreatureDifficultyRepository.class);
    }

    @Test
    public void TestCreatureServiceAddsCreaturesSuccessfully(){
        CreatureService creatureService = new CreatureServiceImpl(mockCreatureRepository, mockCreatureDifficultyRepository);
        Mockito.when(mockCreatureRepository.findByName("Ghost")).thenReturn(testCreature);

        assertFalse(creatureService.add(blackModel));

        Assertions.assertTrue(creatureService.add(creatureAddBindingModel));
        creatureService.add(creatureAddBindingModel);
        Creature expected = testCreature;
        Creature actual = mockCreatureRepository.findByName(testCreature.getName());

        assertEquals(expected, actual);

    }

    @Test
    public void TestCreatureServiceReturnsCreaturesForDisplay(){
        CreatureService creatureService = new CreatureServiceImpl(mockCreatureRepository, mockCreatureDifficultyRepository);
        Mockito.when(mockCreatureRepository.findAll()).thenReturn(List.of(testCreature));

        assertFalse(creatureService.creaturesForDisplay().isEmpty());
    }
}
