package com.lii.cloud.db.redis;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;

/**
 * 自定义Redis序列化
 */
public class EntityRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return null;
        }
        return SerializationUtils.serialize((Serializable)t);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return SerializationUtils.deserialize(bytes);
    }

}
