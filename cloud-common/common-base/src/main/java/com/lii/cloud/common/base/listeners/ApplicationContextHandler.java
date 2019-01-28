package com.lii.cloud.common.base.listeners;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("applicationContextHandler")
public class ApplicationContextHandler implements ApplicationContextAware {
	private static ApplicationContext applicationContexts = null;  

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO 自动生成的方法存根
		if(applicationContexts == null){  
            applicationContexts  = applicationContext;  
        }  
	}
	
	//获取applicationContext  
    public static ApplicationContext getApplicationContexts() {  
        return applicationContexts;  
    }  
  
    //通过name获取 Bean.  
    public static Object getBean(String name){  
        return getApplicationContexts().getBean(name);  
  
    }  
  
    //通过class获取Bean.  
    public static <T> T getBean(Class<T> clazz){  
        return getApplicationContexts().getBean(clazz);  
    }  
  
    //通过name,以及Clazz返回指定的Bean  
    public static <T> T getBean(String name,Class<T> clazz){  
        return getApplicationContexts().getBean(name, clazz);  
    }  

}
