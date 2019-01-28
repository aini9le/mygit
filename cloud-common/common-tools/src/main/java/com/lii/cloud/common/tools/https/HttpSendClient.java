package com.lii.cloud.common.tools.https;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpSendClient {
	
	/**
	 * 发送post请求
	 * 返回字符串 结果
	 * @param url
	 * @param sendData
	 * @return  
	 */
	public static HttpClientStrResponse clientStrPost(String url,String sendData){
		HttpClientStrResponse res = null;
		CloseableHttpClient client = null;
		try {
			res =new HttpClientStrResponse();
			HttpClientBuilder httpclient = HttpClientBuilder.create();
			client = httpclient.build();
			HttpPost post = new HttpPost(url); 
			StringEntity entiy = new StringEntity(sendData,"UTF-8"); 
			post.setEntity(entiy);
			CloseableHttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String resData = EntityUtils.toString(entity, "utf-8"); 
			res.setResponse(resData);
			res.setStatus(response.getStatusLine().getStatusCode());
			System.out.println(" 返回数据= " + resData);
	        EntityUtils.consume(entity);  //关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != client)
					client.close();   //关闭资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 发送post请求
	 * 返回json数据 结果
	 * @param url
	 * @param sendData 
	 * @return
	 */
	public static HttpClientJSONResponse clientJsonPost(String url,String sendData){
		HttpClientJSONResponse res = null;
		CloseableHttpClient client = null;
		try {
			res =new HttpClientJSONResponse();
			HttpClientBuilder httpclient = HttpClientBuilder.create();
			client = httpclient.build();
			HttpPost post = new HttpPost(url); 
			StringEntity entiy = new StringEntity(sendData,"UTF-8"); 
			post.setEntity(entiy);
			CloseableHttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String resData = EntityUtils.toString(entity, "utf-8"); 
			res.setResponse(JSONObject.parseObject(resData));
			res.setStatus(response.getStatusLine().getStatusCode());
			System.out.println(" 返回数据= " + resData);
	        EntityUtils.consume(entity);  //关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != client)
					client.close();   //关闭资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 发送get请求
	 * 返回字符串 结果
	 * @param url
	 * @param sendData
	 * @return
	 */
	public static HttpClientStrResponse clientStrGet(String url){
		HttpClientStrResponse res = null;
		CloseableHttpClient client = null;
		try {
			res =new HttpClientStrResponse();
			HttpClientBuilder httpclient = HttpClientBuilder.create();
			client = httpclient.build();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String resData = EntityUtils.toString(entity, "utf-8"); 
			res.setResponse(resData);
			res.setStatus(response.getStatusLine().getStatusCode());
			System.out.println(" 返回数据= " + resData);
	        EntityUtils.consume(entity);  //关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != client)
					client.close();   //关闭资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 发送get请求
	 * 返回json数据 结果
	 * @param url
	 * @param sendData
	 * @return
	 */
	public static HttpClientJSONResponse clientJsonGet(String url){
		HttpClientJSONResponse res = null;
		CloseableHttpClient client = null;
		try {
			res =new HttpClientJSONResponse();
			HttpClientBuilder httpclient = HttpClientBuilder.create();
			client = httpclient.build();
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String resData = EntityUtils.toString(entity, "utf-8"); 
			res.setResponse(JSONObject.parseObject(resData));
			res.setStatus(response.getStatusLine().getStatusCode());
			System.out.println(" 返回数据= " + resData);
	        EntityUtils.consume(entity);  //关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != client)
					client.close();   //关闭资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	

	public static void main(String[] args) {
		String corpId = "wxf33555fe2dc77155";
		String secret = "mEvHtq-wRcTvz7NRIeOgrgT7OWg7dhiTnbRFmoX1tQw";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpId+"&corpsecret="+secret;
		HttpClientJSONResponse clientJson = clientJsonGet(url);
		System.out.println(" access_token = " + clientJson.getResponse().get("access_token"));
	}
}
