package com.abramchik.taskFive.entity;

import com.abramchik.taskFive.entity.currency.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food extends Product {

    boolean includesGMO;

    public Food(int id, String name, BigDecimal price, boolean includesGMO, Currency currency) {
        super(id, name, price, currency);
        this.includesGMO = includesGMO;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\nFood: " +
                "\n ID - " + super.getId() +
                "\n name - " + super.getName() +
                "\n price - " + super.getPrice() +
                "\n currency - " + super.getCurrency().getName() +
                "\n includesGMO - " + includesGMO;
    }
}
