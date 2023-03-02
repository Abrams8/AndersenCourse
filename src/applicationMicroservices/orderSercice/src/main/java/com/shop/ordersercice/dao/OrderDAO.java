package com.shop.ordersercice.dao;

import com.shop.ordersercice.entity.Order;
import java.util.List;

public interface OrderDAO {

    boolean makeOrder(Order order);

    boolean confirmeOrder(int id);

    List<Order> getAllOrders();

}
