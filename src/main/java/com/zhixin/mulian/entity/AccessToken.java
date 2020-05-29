package com.zhixin.mulian.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccessToken implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 获取到的凭证 
	 */
	private String access_token;
	/**
	 * 凭证有效时间，单位：秒 
	 */
	private Integer expires_in;
	/**
	 * 错误码
	 */
	private Integer errcode;
	/**
	 * 错误消息
	 */
	private String errmsg;
}
