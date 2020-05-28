package com.zhixin.mulian.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.zhixin.mulian.service.IUserService;
import com.zhixin.mulian.utils.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shi
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	public Result<Object> users(){
		return userService.getAllUser();
	}
}
