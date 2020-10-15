package com.wanghongen.demo.apidemo.service.impl;

import com.wanghongen.demo.apidemo.model.Order;
import com.wanghongen.demo.apidemo.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order getOrderById(Integer id) {
        Order order = new Order();
        order.setId(id);
        order.setName("wanghongen王红恩");
        return order;
    }
}
