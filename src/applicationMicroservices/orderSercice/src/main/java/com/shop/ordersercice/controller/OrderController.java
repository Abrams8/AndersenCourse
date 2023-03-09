package com.shop.ordersercice.controller;

import com.shop.ordersercice.entity.Order;
import com.shop.ordersercice.service.OrderService;
import com.shop.ordersercice.service.impl.OrderServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private static final String USER = "USER";

    OrderService orderService = new OrderServiceImpl();

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders(@RequestHeader String role){
        if(USER.equals(role)){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/order")
    public ResponseEntity<Boolean> makeOrder(@RequestBody Order order, @RequestHeader String role){
        if(USER.equals(role)){
            return ResponseEntity.status(404).body(false);
        }
        return ResponseEntity.ok(orderService.makeOrder(order));
    }

    @PutMapping("/order")
    public ResponseEntity<Boolean> confirmeOrder(@RequestParam int id, @RequestHeader String role){
        if(USER.equals(role)){
            return ResponseEntity.status(404).body(false);
        }
        return ResponseEntity.ok(orderService.confirmeOrder(id));
    }

}
