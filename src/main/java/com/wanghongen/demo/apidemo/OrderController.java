package com.wanghongen.demo.apidemo;

import com.wanghongen.demo.apidemo.model.Order;
import com.wanghongen.demo.apidemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @GetMapping("{id}")
    public Order getOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        return order;
    }

}
