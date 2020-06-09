package com.zhixin.mulian.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 微信小程序登录验证接口返回的对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WXLogin {
	private String errcode;
	private String errmsg;
	private String openid;
	private String session_key;
	private String unionid;
}
