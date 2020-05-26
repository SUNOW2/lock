package com.redis.lock.service;

import com.redis.lock.entity.OrderEntity;
import com.redis.lock.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.redis.lock.utils.RedisUtils.getValue;

/**
 * 描述：订单服务层
 *
 * @ClassName OrderService
 * @Author 徐旭
 * @Date 2020-04-15 17:14
 * @Version 1.0
 */
@Service
public class OrderService {

    @Value("${redis.default.expireTime}")
    private Long expireTime;

    /**
     * 新增订单
     *
     * @param entity
     */
    public void addOrder(OrderEntity entity) {

        String orderNo = entity.getOrderNo();
        String value = getValue();
        // 使用redis加锁
        Integer result = RedisUtils.tryLock(orderNo, value, expireTime);
        if (result == 1) {
            try {
                // 做相关操作
                System.out.println("我在新增订单");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                RedisUtils.unLock(orderNo, value);
            }
        }
    }
}
