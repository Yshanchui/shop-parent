package com.yshanchui.service.impl;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yshanchui.domain.Order;
import com.yshanchui.domain.Product;
import com.yshanchui.mapper.OrderMapper;
import com.yshanchui.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService   {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productId, Long userId) {
        //创建订单对象，填充订单信息
        Order order = new Order();
        order.setNumber(1);
        order.setPid(productId);
        order.setUid(userId);
        order.setUsername("user:"+userId);

        //根据商品id，查询商品信息
        // TODO 如何基于 id 查询商品信息？ ==> 利用springboot的restTemplate对象发送HTTP请求
        String url = "http://localhost:8081/products/"+productId;
        Product product = restTemplate.getForObject("url", Product.class);
        Assert.notNull((IErrorCode) product,"商品对象不存在");
        //填充商品信息到订单
        order.setProductName(product.getName());
        //保存订单对象
        order.setProductPrice(product.getPrice());

        super.save(order);
        return order;

    }
}
