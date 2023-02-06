package com.abramchik.taskFive.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotFood extends Product{

    int warrantyPeriod;

    public NotFood(int id, String name, Double price, int warrantyPeriod) {
        super(id, name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\n NotFood: " +
                "\n ID - " + super.getId() +
                "\n name - " + super.getName() +
                "\n price - " +super.getPrice() +
                "\n warranty period - " + warrantyPeriod;
    }
}
