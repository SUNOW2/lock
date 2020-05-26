package com.redis.lock.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述：redis工具类
 *
 * @ClassName RedisUtils
 * @Author 徐旭
 * @Date 2020-04-15 17:12
 * @Version 1.0
 */
@Component
public class RedisUtils {

    private static RedisTemplate template;

    @Autowired
    public RedisUtils(@Qualifier("redisTemplate") RedisTemplate template) {
        RedisUtils.template = template;
    }
    /**
     * 新增redis记录
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public static Integer tryLock(String key, String value, Long expire) {
        Boolean result = template.opsForValue().setIfAbsent(key, value, expire, TimeUnit.MILLISECONDS);
        if (result == true) {
            return 1;
        }
        return 0;
    }

    /**
     * 获取redis锁的值
     *
     * @return
     */
    public static String getValue() {
        long milli = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "_");
        return milli + uuid;
    }

    /**
     * 删除redis记录
     *
     * @param key
     * @param value
     * @return
     */
    public static void unLock(String key, String value) {
        String remoteValue = template.opsForValue().get(key).toString();
        if (StringUtils.isNotBlank(remoteValue) &&
                remoteValue.equals(value)) {
            template.opsForValue().getOperations().delete(key);
        }
    }
}
