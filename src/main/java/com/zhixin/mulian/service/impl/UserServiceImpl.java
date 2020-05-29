package com.zhixin.mulian.service.impl;

import com.zhixin.mulian.entity.User;
import com.zhixin.mulian.mapper.UserMapper;
import com.zhixin.mulian.service.IUserService;
import com.zhixin.mulian.utils.RedisTool;
import com.zhixin.mulian.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shi
 * @since 2020-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisTool redisTool;

	@Override
	public Result<Object> getAllUser() {
		List<User> list=userMapper.selectList(null);
		redisTool.set("wechat_token", 11111);
		return Result.ok(list);
	}

}
