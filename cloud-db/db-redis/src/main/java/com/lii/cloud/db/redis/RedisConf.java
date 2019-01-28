package com.lii.cloud.db.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 */
@Configuration
//@EnableRedisHttpSession  //开启spring session支持
public class RedisConf {
    private static final Log log = LogFactory.getLog(RedisConf.class);

    // Redis服务器地址
    @Value("${redis.host}")
    private String host;
    // Redis服务器连接端口
    @Value("${redis.port}")
    private int port;
    // Redis服务器连接密码（默认为空）
    @Value("${redis.password}")
    private String password;
    // 连接超时时间（毫秒）
    @Value("${redis.timeout}")
    private int timeout;
    // 连接超时时间（毫秒）
    @Value("${redis.database}")
    private int database;
    // 连接池最大连接数（使用负值表示没有限制）
    @Value("${redis.pool.max-active}")
    private int maxTotal;
    // 连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${redis.pool.max-wait}")
    private int maxWaitMillis;
    // 连接池中的最大空闲连接
    @Value("${redis.pool.max-idle}")
    private int maxIdle;
    // 连接池中的最小空闲连接
    @Value("${redis.pool.min-idle}")
    private int minIdle;



    /**
     * 配置JedisPoolConfig
     * @return JedisPoolConfig实体
     */
    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        log.info("初始化JedisPoolConfig");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 连接池最大连接数（使用负值表示没有限制）
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        // 连接池最大阻塞等待时间（使用负值表示没有限制）
        jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
        // 连接池中的最大空闲连接
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        // 连接池中的最小空闲连接
        jedisPoolConfig.setMinIdle(this.minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnCreate(true);
//        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }

    /**
     * JedisPool 连接池
     * @param poolConfig
     * @return
     */
    @Bean(name = "jedisPool")
    public JedisPool jedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig){
    	JedisPool jedisPool = null;
    	if(StringUtils.isNotBlank(password))
    		jedisPool = new JedisPool(poolConfig, host,port,timeout,password);
    	else{
    		jedisPool = new JedisPool(poolConfig, host);
    	}
    	return jedisPool;
    }
    
    /**
     * 集群配置
     * @param poolConfig
     * @return
     */
   /* @Bean(name = "jedisCluster")
    public JedisCluster jedisCluster(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig){
    	// 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("192.168.56.180", 7777));
        hostAndPortsSet.add(new HostAndPort("192.168.56.180", 8888));
        hostAndPortsSet.add(new HostAndPort("192.168.56.181", 7777));
        hostAndPortsSet.add(new HostAndPort("192.168.56.181", 8888));
        JedisCluster jedisCluster = new JedisCluster(hostAndPortsSet, poolConfig);
    	return jedisCluster;
    }*/

    /**
     * 实例化 RedisConnectionFactory 对象
     * @param poolConfig
     * @return
     */
    @Bean(name = "jedisConnectionFactory")
    public RedisConnectionFactory jedisConnectionFactory(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig poolConfig) {
        log.info("初始化RedisConnectionFactory");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(this.host);
        jedisConnectionFactory.setPort(this.port);
        jedisConnectionFactory.setDatabase(this.database);
        jedisConnectionFactory.setPassword(this.password);
        return jedisConnectionFactory;
    }

    /**
     *  实例化 RedisTemplate 对象
     * @return
     */
    /*@Bean(name = "redisTemplate")
    public RedisTemplate<String, Serializable> redisTemplate(@Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory factory) {
        log.info("初始化RedisTemplate");

        RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new EntityRedisSerializer());
//        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        template.setEnableTransactionSupport(true);
        return template;
    }*/

}
