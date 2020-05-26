package com.redis.lock.controller;

import com.redis.lock.entity.OrderEntity;
import com.redis.lock.service.OrderService;
import com.redis.lock.test.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：
 *
 * @ClassName OrderController
 * @Author 徐旭
 * @Date 2020-04-15 17:12
 * @Version 1.0
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "addOrder")
    public void addOrder(@RequestBody OrderEntity entity) {
        entity.setOrderNo("No20200415001");
        orderService.addOrder(entity);
    }

    @GetMapping(value = "test")
    public void test() {
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyThread(orderService)).start();
        }
    }
}
