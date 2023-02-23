package com.shop.productservice.entity;

import com.shop.productservice.entity.currency.Currency;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotFood extends Product{

    int warrantyPeriod;

    public NotFood(int id, String name, BigDecimal price, int warrantyPeriod, Currency currency) {
        super(id, name, price, currency);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\n NotFood: " +
                "\n ID - " + super.getId() +
                "\n name - " + super.getName() +
                "\n price - " +super.getPrice() +
                "\n currency - " + super.getCurrency().getName() +
                "\n warranty period - " + warrantyPeriod;
    }
}
