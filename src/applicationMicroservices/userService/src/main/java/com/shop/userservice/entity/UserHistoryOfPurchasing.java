package com.shop.userservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class UserHistoryOfPurchasing {

    Date tradeDate;
    int orderId;
    BigDecimal orderSum;

}
