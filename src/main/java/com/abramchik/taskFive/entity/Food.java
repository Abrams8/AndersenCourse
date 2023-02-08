package com.abramchik.taskFive.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food extends Product {

    boolean includesGMO;

    public Food(int id, String name, Double price, boolean includesGMO) {
        super(id, name, price);
        this.includesGMO = includesGMO;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\nFood: " +
                "\n ID - " + super.getId() +
                "\n name - " + super.getName() +
                "\n price - " + super.getPrice() +
                "\n includesGMO - " + includesGMO;
    }
}
