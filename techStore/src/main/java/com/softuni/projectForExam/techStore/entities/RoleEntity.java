package com.softuni.projectForExam.techStore.entities;

import com.softuni.projectForExam.techStore.entities.enums.RolesEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    public RolesEnum getName() {
        return name;
    }

    public void setName(RolesEnum name) {
        this.name = name;
    }
}
