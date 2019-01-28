package com.lii.cloud.db.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.LocalDate;
import java.time.Period;


@Component
public class IdSeqGenerator {
	/**
	 * 开始时间
	 */
	private static LocalDate beginDate = LocalDate.of(2018, 11, 20);
	
	 /**
     * 昵称计数器，确保唯一性
     */
    private String ID_COUNTER_REDISKEY = "ID_DEFAULT:";
    /**
     * ID前缀
     */
    private static final String NICKNAME_PREFIX = "18";
    /**
     * 日期起始点
     * 2018-06-27 -->>1530028800000L
     * 2018-09-11 -->>1536595200000L
     * 1463108596098L
     */
    private final static long epoch= 1536595200000L;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 初始化redis计数器 开始
     */
    @PostConstruct
    private Jedis init() {
    	Jedis jedis = jedisPool.getResource();
    	if (null != jedis && jedis.get(ID_COUNTER_REDISKEY) == null) {
    		jedis.set(ID_COUNTER_REDISKEY, "1");
    	}
    	return jedis;
    }
    
    public static void main(String[] args) {
		/*LocalDate endDate = LocalDate.now();
		Period period = Period.between(beginDate,endDate);
		System.out.println(period.getDays()+"1000000");
		System.out.println(String.format("%0" + 4 + "d", 222));*/
//    	Long l = DateUtil.stringToDate("2018-09-11").getTime();
//    	System.out.println(l);
//		System.out.println(((System.currentTimeMillis() - l) / (60000*24*60)));
//		System.out.println(((System.currentTimeMillis() - epoch) / 60000));
//		System.out.println(((System.currentTimeMillis() - epoch) / 60000));
//		System.out.println(((System.currentTimeMillis() - epoch) / 60000));
	}
    
    /**
     * 下一个id
     * @return
     */
    public String nextId() {
    	String nextId = null;
    	Jedis jedis = init();
    	try {
    		// 起始时间到当前时间的分钟数作为随机因子, 用来减少全是1个数的出现情况
    		long value = jedis.incr(ID_COUNTER_REDISKEY);
			LocalDate endDate = LocalDate.now();
			Period period = Period.between(beginDate,endDate);
    		nextId = NICKNAME_PREFIX + String.valueOf(period.getDays()) + String.format("%0" + 7 + "d", value);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		if (null != jedis){
				jedis.close();
			}
		}
    	return nextId;
    }

    /**
     * 设置id表示
     * @param key
     */
    public void setKey(String key){
    	ID_COUNTER_REDISKEY = key;
    }
}
