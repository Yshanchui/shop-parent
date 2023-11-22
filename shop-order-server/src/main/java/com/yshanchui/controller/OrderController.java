package com.yshanchui.controller;

import com.yshanchui.domain.Order;
import com.yshanchui.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public Order createOrder(Long productId ,Long userId) {
        return orderService.createOrder(productId,userId);
    }
}
