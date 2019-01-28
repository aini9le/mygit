package com.lii.cloud.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ason on 2017/9/25.
 * 这里实现CachingConfigurerSupport主要是方便使用自定义keyGenerator
 */
@Configuration
// 启用缓存
@EnableCaching
public class RedisCacheConf  extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate<?, ?> redisTemplate;

    private static final Log log = LogFactory.getLog(RedisCacheConf.class);
    /**
     * 配置redis缓存管理对象
     * @return
     */
    @Bean(name = "cacheManager")
    @Override
    public CacheManager cacheManager() {
        log.info("初始化CacheManager");
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 开启使用缓存名称最为key前缀
//        cacheManager.setUsePrefix(true);
        //这里可以设置一个默认的过期时间 单位是秒
        cacheManager.setDefaultExpiration(30 * 60);
     // 设置缓存的过期时间
        Map<String, Long> expires = new HashMap<String,Long>();
        expires.put("people", 1000L);
//      expires.put("cache:user", 36000L);
        cacheManager.setExpires(expires);
        return cacheManager;
    }

    /**
     * 生成key的策略
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
