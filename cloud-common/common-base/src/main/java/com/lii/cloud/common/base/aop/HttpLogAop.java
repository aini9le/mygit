package com.lii.cloud.common.base.aop;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lii.cloud.common.tools.mongo.Person;
import com.lii.cloud.common.tools.utils.BlankUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * 请求AOP
 */
//定义一个切面
@Aspect
@Component
public class HttpLogAop {
	@Autowired
	private MongoTemplate template;

    private static final Log log = LogFactory.get();

    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.lii.cloud.common.config.controller.*.*(..)) || execution(public * com.lii.cloud.admin.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // 记录时间
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        log.info("url={}", request.getRequestURL());

        //method
        log.info("method={}", request.getMethod());

        //ip
        log.info("ip={}", request.getRemoteAddr());

        //类方法
        log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        log.info("参数args={}", joinPoint.getArgs());
        
        /*Person per = new Person();
        per.setName("aaaa");
        per.setSex("男");
        per.setAge(10);
        template.save(per);*/
        List<Person> list = template.find(query(where("age").is(10)), Person.class); 
        System.out.println("查询的数据量："+list.size());
    }

    @After("log()")
    public void doAfter() {
        log.info("222222222222");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        if (!BlankUtil.isBlank(object)) {
            log.info("response={}", object.toString());
        }

        log.info("spend time : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }

}
