package com.yshanchui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yshanchui.domain.Order;

public interface IOrderService extends IService<Order> {
    Order createOrder(Long productId, Long userId);
}
