package com.shop.ordersercice.service.impl;

import com.shop.ordersercice.dao.OrderDAO;
import com.shop.ordersercice.dao.impl.OrderDAOImpl;
import com.shop.ordersercice.entity.Order;
import com.shop.ordersercice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean makeOrder(Order order) {
        return orderDAO.makeOrder(order);
    }

    @Override
    public boolean confirmeOrder(int id) {
        return orderDAO.confirmeOrder(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }
}
