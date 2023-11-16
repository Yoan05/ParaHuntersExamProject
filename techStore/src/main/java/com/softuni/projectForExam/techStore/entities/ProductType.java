package com.softuni.projectForExam.techStore.entities;

import com.softuni.projectForExam.techStore.entities.enums.ProductTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "productTypes")
public class ProductType extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ProductTypeEnum type;
    private String description;

    public ProductTypeEnum getType() {
        return type;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
        setDescription(type);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(ProductTypeEnum type) {
        if (type.equals(ProductTypeEnum.GADGET)){
            this.description = "Gadget";
        } else if (type.equals(ProductTypeEnum.MELEE_WEAPON)){
            this.description = "Melee weapon";
        } else if (type.equals(ProductTypeEnum.RANGED_WEAPON)){
            this.description = "Ranged weapon";
        }
    }
}
