package com.shop.ordersercice.service;

import com.shop.ordersercice.entity.Order;
import java.util.List;

public interface OrderService {

    boolean makeOrder(Order order);

    boolean confirmeOrder(int id);

    List<Order> getAllOrders();
}
