package com.wanghongen.demo.apidemo;

import com.wanghongen.demo.apidemo.anno.ResponseResult;
import com.wanghongen.demo.apidemo.model.Order;
import com.wanghongen.demo.apidemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 看看人家那后端API接口写得，那叫一个优雅！
 * https://mp.weixin.qq.com/s?__biz=MzA5NDg3MjAwMQ==&mid=2457108749&idx=1&sn=75d3cd75344ada80d7a051e9cf71efe1&chksm=87c829a3b0bfa0b5bcc074cfafbde04e58eb2b0ae45166538b0ced733e1cc1d55ec7ff49e8fd&mpshare=1&scene=1&srcid=10154puhngaqobkhUBOvkfAW&sharer_sharetime=1602726578859&sharer_shareid=2d6a366955a823bb21fcc7b471ea1b74&key=0e194573360c8aa2a2adaf6bbf4b1db601117f29b1ea8aaedec7e3be3f63115263bb7d82b97dedf658756dc12931b346ded52cda722f076e9dee9de10eced5a48cfbfb917ad44315f08c433efb1db7065a4045977d28be5edd69f10287bc9c87b9d56029515e5017dfc5e9fd32762b489ba532181d872c1426072d8bff2c323d&ascene=1&uin=NTY0NDYwNTk3&devicetype=Windows+10+x64&version=6300002f&lang=zh_CN&exportkey=AxBzukEjBI8BgYw7JPqmyjs%3D&pass_ticket=r7Xy0MGiEt3SfXG%2BPpHZuPp2egwUU5hiz1fBJydXjMkIHxL7IIgGDN6Z%2FKVH3Q5Z&wx_header=0
 */
@RestController
@RequestMapping("/orders")
@ResponseResult
public class OrderController {

    @Autowired
    OrderService orderService;
    @GetMapping("{id}")
    public Order getOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getOrderById(id);
        return order;
    }

}
