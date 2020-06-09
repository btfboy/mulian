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
import com.zhixin.mulian.utils.WXUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableScheduling
public class WechatTocken {
	

	
	@Autowired
	private RedisTool redisTool;
	
	//毫秒
	@Scheduled(fixedRate=1000*60*100)
	private void getAccessToken() {
		AccessToken token=WXUtils.getAccessToken();
		redisTool.set("wechat_token", token);
	}
	
	public static void main(String[] args) {
		AccessToken token=WXUtils.getAccessToken();
		if(token!=null) {
			System.out.println(token.getAccess_token());
		}
	}
	
}
