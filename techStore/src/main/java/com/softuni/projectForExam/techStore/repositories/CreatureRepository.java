package com.softuni.projectForExam.techStore.repositories;

import com.softuni.projectForExam.techStore.entities.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {
    Creature findByName(String ghost);
}
