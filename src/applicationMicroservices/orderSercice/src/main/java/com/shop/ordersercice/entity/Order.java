package com.shop.ordersercice.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class Order {

    int id;
    boolean processed;
    Date date;
    int bucketId;
    BigDecimal sum;

    public Order(int bucketId, BigDecimal sum) {
        this.date = java.sql.Date.valueOf(LocalDate.now().toString());
        this.bucketId = bucketId;
        this.sum = sum;
    }
}
