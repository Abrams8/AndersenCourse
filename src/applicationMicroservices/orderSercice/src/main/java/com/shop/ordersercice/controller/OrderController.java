package com.shop.ordersercice.controller;

import com.shop.ordersercice.entity.Order;
import com.shop.ordersercice.service.OrderService;
import com.shop.ordersercice.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService = new OrderServiceImpl();

    @GetMapping()
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/make")
    public boolean makeOrder(@RequestBody Order order){
        return orderService.makeOrder(order);
    }

    @PutMapping("/confirme")
    public boolean confirmeOrder(@RequestParam int id){
        return orderService.confirmeOrder(id);
    }

}
