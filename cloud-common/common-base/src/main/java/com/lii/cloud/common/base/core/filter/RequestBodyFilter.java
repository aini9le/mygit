package com.lii.cloud.common.base.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//@Component
public class RequestBodyFilter implements Filter {


	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
		// TODO 自动生成的方法存根
		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
        }
        if (null == requestWrapper) {
        	filterchain.doFilter(request, response);
        } else {
        	filterchain.doFilter(requestWrapper, response);
        }
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO 自动生成的方法存根
	}

}
