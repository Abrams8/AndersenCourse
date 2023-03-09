package com.abramchik.taskFive.dao;

import com.abramchik.taskFive.entity.Order;

public interface OrderDAO {

    boolean makeOrder(Order order);

    boolean confirmeOrder(Order order);
}
