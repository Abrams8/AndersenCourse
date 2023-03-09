package com.abramchik.taskFive.controller;

import com.abramchik.taskFive.entity.Order;
import com.abramchik.taskFive.service.OrderService;
import com.abramchik.taskFive.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping(("/orders"))
public class OrderController {
    OrderService orderService = new OrderServiceImpl();

    @PostMapping("/make")
    public boolean makeOrder(@RequestParam Integer bucketId, @RequestParam BigDecimal sum){
        Order order = new Order();
        order.setDate(java.sql.Date.valueOf(LocalDate.now().toString()));
        order.setBucketId(bucketId);
        order.setSum(sum);
        return orderService.makeOrder(order);
    }
}
