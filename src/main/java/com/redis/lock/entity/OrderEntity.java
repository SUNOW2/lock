package com.redis.lock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 描述：订单信息
 *
 * @ClassName OrderEntity
 * @Author 徐旭
 * @Date 2020-04-15 17:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderEntity {
    /**
     * 编号
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;
}
