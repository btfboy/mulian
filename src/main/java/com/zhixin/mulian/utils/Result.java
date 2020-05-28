package com.zhixin.mulian.utils;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String msg = "操作成功！";

	/**
	 * 返回代码
	 */
	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */
	private T result;
	
	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();
	
	private Object extra;

	public Result() {
		
	}
	
	public Result<T> error500(String msg) {
		this.msg = msg;
		this.code = Constant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
		return this;
	}
	
	public Result<T> success(String msg) {
		this.msg = msg;
		this.code = Constant.SC_OK_200;
		this.success = true;
		return this;
	}
	
	
	public static Result<Object> ok() {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(Constant.SC_OK_200);
		r.setMsg("成功");
		return r;
	}
	
	public Result<T> success(T t) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(Constant.SC_OK_200);
		r.setMsg("成功");
		r.setResult(t);
		return r;
	}
	
	public static Result<Object> ok(String msg) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(Constant.SC_OK_200);
		r.setMsg(msg);
		return r;
	}
	
	public static Result<Object> ok(Object data) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(Constant.SC_OK_200);
		r.setResult(data);
		return r;
	}
	
	public static Result<Object> error(String msg) {
		return error(Constant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMsg(msg);
		r.setSuccess(false);
		return r;
	}
	
	/**
	 * 无权限访问返回结果
	 */
	public static Result<Object> noauth(String msg) {
		return error(Constant.SC_NO_AUTHZ, msg);
	}
	/**
	 * 未登录
	 */
	public static Result<Object> noUser() {
		return error(Constant.SC_NO_USER, "请先登录。");
	}
	
	public Result<T> noUser(T t) {
		Result<T> r = new Result<T>();
		r.setSuccess(false);
		r.setCode(Constant.SC_NO_USER);
		r.setMsg("请先登录。");
		r.setResult(null);
		return r;
	}

	/**
	 * api未登录
	 */
	public static Result<Object> noCustomer() {
		return error(Constant.SC_API_NO_USER_CODE, "请先登录。");
	}
	public Result<T> noCustomer(T t) {
		Result<T> r = new Result<T>();
		r.setSuccess(false);
		r.setCode(Constant.SC_API_NO_USER_CODE);
		r.setMsg("请先登录。");
		r.setResult(null);
		return r;
	}
	
	public static Result<Object> operation(int a) {
		Result<Object> result = new Result<Object>();
		if(1==a) {
			result.setCode(Constant.SC_OK_200);
			result.setMsg("操作成功！");
		}else {
			result.setCode(Constant.SC_INTERNAL_SERVER_ERROR_500);
			result.setMsg("操作失败！");
		}
		result.setSuccess(true);
		return result;
	}
}