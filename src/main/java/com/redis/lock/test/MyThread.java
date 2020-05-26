package com.redis.lock.test;

import com.redis.lock.entity.OrderEntity;
import com.redis.lock.service.OrderService;

/**
 * 描述：redis分布式锁测试类
 *
 * @ClassName MyThread
 * @Author 徐旭
 * @Date 2020-04-16 09:02
 * @Version 1.0
 */
public class MyThread implements Runnable {


    private OrderService orderService;

    public MyThread(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        String threadId = "No202004160001";
        OrderEntity entity = new OrderEntity().setOrderNo(threadId);
        orderService.addOrder(entity);
    }

}
