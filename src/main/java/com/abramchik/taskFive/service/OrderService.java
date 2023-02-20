package com.abramchik.taskFive.service;

import com.abramchik.taskFive.entity.Order;

public interface OrderService {

    boolean makeOrder(Order order);

    boolean confirmeOrder(Order order);

}
