package com.abramchik.taskFive.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class Order {
    int id;
    boolean processed;
    Date date;
    int bucketId;
    BigDecimal sum;
}
