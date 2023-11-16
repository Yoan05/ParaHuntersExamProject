package com.softuni.projectForExam.techStore.init;

import com.softuni.projectForExam.techStore.entities.ProductType;
import com.softuni.projectForExam.techStore.entities.enums.ProductTypeEnum;
import com.softuni.projectForExam.techStore.repositories.ProductTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ProductTypeInit implements CommandLineRunner {
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeInit(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public void run(String... args) {
        boolean hasAny = !productTypeRepository.findAll().isEmpty();
        if (!hasAny){
            List<ProductType> types = new ArrayList<>();
            Arrays.stream(ProductTypeEnum.values()).forEach(type -> {
                ProductType productType = new ProductType();
                productType.setType(type);
                types.add(productType);
            });
            productTypeRepository.saveAll(types);
        }
    }
}
