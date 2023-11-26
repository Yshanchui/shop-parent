package com.yshanchui.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yshanchui.domain.Order;
import com.yshanchui.domain.Product;
import com.yshanchui.fegin.ProductFeignApi;
import com.yshanchui.mapper.OrderMapper;
import com.yshanchui.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService   {

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private  ProductFeignApi productFeignApi;

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
//        String url = "http://localhost:8081/products/"+productId;
        //利用服务发现客户端，获取想要的服务的地址信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
//        // 当获取到多个服务实例的时候，需要对其进行负载均衡==》随机
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance instance = instances.get(index);

//        String url = "http://"+ instance.getHost() +":"+instance.getPort()+"/products/"+productId;
//        String url = "http://product-service/products"+productId;
//        Product product = restTemplate.getForObject(url, Product.class);
        //基于feign client 进行远程调用
        Product product = productFeignApi.findById(productId);
        Assert.notNull(product,"商品对象不存在");
        //填充商品信息到订单
        order.setProductName(product.getName());
        //保存订单对象
        order.setProductPrice(product.getPrice());

        super.save(order);
        return order;

    }
}
