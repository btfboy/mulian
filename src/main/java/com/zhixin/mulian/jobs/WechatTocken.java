package com.zhixin.mulian.jobs;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSON;
import com.zhixin.mulian.entity.AccessToken;
import com.zhixin.mulian.utils.RedisTool;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableScheduling
public class WechatTocken {
	
	private static final String appid="wx29a3d68013f9ec5b";
	private static final String secret="7bff3b25a325561547504344f54f7bf9";
	
	@Autowired
	private RedisTool redisTool;
	
	//毫秒
	@Scheduled(fixedRate=1000*60*100)
	private void getAccessToken() {
		AccessToken token=getAccessToken(appid,secret);
		redisTool.set("wechat_token", token);
	}
	
	public static AccessToken getAccessToken(String appid,String secret){
		String grant_type ="client_credential";//获取access_token，则固定填写client_credential 
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret;
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
	
	public static void main(String[] args) {
		AccessToken token=getAccessToken(appid,secret);
		if(token!=null) {
			System.out.println(token.getAccess_token());
		}
	}
	
}
