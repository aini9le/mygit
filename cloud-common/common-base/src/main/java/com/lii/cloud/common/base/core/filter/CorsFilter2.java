package com.lii.cloud.common.base.core.filter;
//package com.lii.cloud.common.base.core.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Component;
//
//import com.xiaoleilu.hutool.log.Log;
//import com.xiaoleilu.hutool.log.LogFactory;
//
///**
// * 使用过滤器解决跨域问题
// */
////@Component
//public class CorsFilter implements Filter {
//    private static final Log log = LogFactory.get();
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        /*HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//        log.info("*********************************过滤器被使用**************************");
//        ShiroHttpServletRequest requ = (ShiroHttpServletRequest) req;
//        ShiroHttpServletResponse resShiro = (ShiroHttpServletResponse) res;
//        System.out.println("路径 = "+requ.getRequestURL());
//        System.out.println(" sessionid = "+requ.getSession().getId());
//        chain.doFilter(requ, resShiro);*/
//    	
//    	ServletRequest requestWrapper = null;  
//        if(req instanceof HttpServletRequest) {  
//            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) req);  
//        }  
//        if(null == requestWrapper) {  
//            chain.doFilter(req, res);  
//        } else {  
//            chain.doFilter(requestWrapper, res);  
//        }  
//          
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    	log.info("-------------初始化过滤器-------------");
//    }
//
//    @Override
//    public void destroy() {
//    	log.info("------------------销毁------------------");
//    }
//}
