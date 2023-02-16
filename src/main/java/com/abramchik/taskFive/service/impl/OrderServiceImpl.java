package com.abramchik.taskFive.service.impl;

import com.abramchik.taskFive.dao.OrderDAO;
import com.abramchik.taskFive.dao.impl.OrderDAOImpl;
import com.abramchik.taskFive.entity.Order;
import com.abramchik.taskFive.service.OrderService;

public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean makeOrder(Order order) {
        return orderDAO.makeOrder(order);
    }

    @Override
    public boolean confirmeOrder(Order order) {
        return orderDAO.confirmeOrder(order);
    }
}
