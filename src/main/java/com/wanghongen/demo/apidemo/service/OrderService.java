package com.wanghongen.demo.apidemo.service;

import com.wanghongen.demo.apidemo.model.Order;
import org.springframework.stereotype.Service;


public interface OrderService {

    Order getOrderById(Integer id);

}
