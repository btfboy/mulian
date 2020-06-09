package com.zhixin.mulian.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.zhixin.mulian.entity.AccessToken;
import com.zhixin.mulian.entity.WXLogin;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class WXUtils {
	
	private static final String APPID="wx29a3d68013f9ec5b";
	
	private static final String SECRET="7bff3b25a325561547504344f54f7bf9";
	
	public static AccessToken getAccessToken(){
		String grant_type ="client_credential";//获取access_token，则固定填写client_credential 
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+APPID+"&secret="+SECRET;
		AccessToken getAccessToken=null;
		try {
			HttpClient client = HttpClientBuilder.create().build();//构建一个Client
			HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
			HttpResponse response = client.execute(get);//提交GET请求
			HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
			String json = EntityUtils.toString(result, "utf-8");
			log.info("获取token"+json);
			getAccessToken = JSON.parseObject(json, AccessToken.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAccessToken;
	}
	
	public static WXLogin getOpenId(String code){
		String grant_type ="authorization_code";
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type="+grant_type;
		WXLogin login=null;
		try {
			HttpClient client = HttpClientBuilder.create().build();//构建一个Client
			HttpGet get = new HttpGet(url.toString());    //构建一个GET请求
			HttpResponse response = client.execute(get);//提交GET请求
			HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
			String json = EntityUtils.toString(result, "utf-8");
			log.info("获取openId"+json);
			login = JSON.parseObject(json, WXLogin.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}
}
