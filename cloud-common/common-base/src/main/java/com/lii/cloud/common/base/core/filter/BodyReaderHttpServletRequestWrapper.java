package com.lii.cloud.common.base.core.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	private byte[] body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		if(request.getMethod().equals(HttpMethod.POST.name())){
			try {
				body = StreamUtils.copyToByteArray(request.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

    	if(null != body){
    		final ByteArrayInputStream bais = new ByteArrayInputStream(body);

            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return bais.read();
                }

    			@Override
    			public boolean isFinished() {
    				// TODO 自动生成的方法存根
    				return false;
    			}

    			@Override
    			public boolean isReady() {
    				// TODO 自动生成的方法存根
    				return false;
    			}

    			@Override
    			public void setReadListener(ReadListener readlistener) {
    				// TODO 自动生成的方法存根
    				
    			}
            };
    	}
        return null;
    }
	
	
	public static String getBodyString(ServletRequest request) {
		StringWriter writer = new StringWriter();
		String str = null;
		try {
			IOUtils.copy(request.getInputStream(), writer, StandardCharsets.UTF_8.name());
			str = writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != writer)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return str;
    }

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

}
