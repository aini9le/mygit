package com.lii.cloud.common.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IDUtils {
private static final Logger logger = LoggerFactory.getLogger(IDUtils.class);
	
	private IDUtils(){
		try {
			logger.error("禁止对主键生成策略实例化");
			throw new Exception("禁止实例化");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/** 
	 * 锁对象，可以为任意对象 
	 */  
	private static Object lockObj = "IDUtil";  

	/**
	 *  业务编码   默认业务编码1
	 */
	private static String businessCode = "10";

	/**
	 * 集群 编码  默认分布编码1
	 */
	private static String distributedCode = "1";

	/** 
	 * 主键生成计数器 
	 */  
	private static long orderNumCount = 0L;  
	
	/** 
	 * 每毫秒生成数量最大值 
	 */  
	private static int maxPerMSECSize=1000;  

	/**
	 * 获取下一个id值
	 * @return
	 */
	public static String nextId(){
		try {  
			// 最终生成的主键号  
			String finOrderNum = "";  
//			long nowLong = Long.parseLong(format.format(new Date()));  
			long nowLong = System.currentTimeMillis();  
			synchronized (lockObj) {  
				// 取系统当前时间作为订单号变量前半部分，精确到毫秒  
				// 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万  
				if (orderNumCount > maxPerMSECSize) {  
					orderNumCount = 0L;  
				}  
				orderNumCount++; 
			}  
			String countStr=maxPerMSECSize +orderNumCount+"";  
			finOrderNum=businessCode + distributedCode + nowLong+countStr.substring(1);  
			return finOrderNum;
		} catch (Exception e) {  
			logger.error("获取主键失败....");
			e.printStackTrace();  
		}  
		return null;
	}
	
	/*public static long nextLongId(){
		Random randomno = new Random(System.currentTimeMillis());
		long value = 0L;
		synchronized(randomno){
			value = randomno.nextLong();
		}
		return value;
	}*/
	
	public static void main(String[] args) {
		try {  
            for (int i = 0; i < 200; i++) {  
                Thread t1 = new Thread(new Runnable() {  
                    public void run() {  
//                        MakeOrderNum makeOrder = new MakeOrderNum();  
//                        System.out.println(IDUtils.nextId());
//                    	IDUtils.nextId();
                    }  
                }, "at" + i);  
                t1.start();  
  
                Thread t2 = new Thread(new Runnable() {  
                    public void run() {  
//                        MakeOrderNum makeOrder = new MakeOrderNum();  
//                        System.out.println(IDUtils.nextId());
//                    	IDUtils.nextId();
                    }  
                }, "bt" + i);  
                t2.start();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
