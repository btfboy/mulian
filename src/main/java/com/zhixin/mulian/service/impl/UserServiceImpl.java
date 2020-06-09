package com.zhixin.mulian.service.impl;

import com.zhixin.mulian.entity.User;
import com.zhixin.mulian.entity.WXLogin;
import com.zhixin.mulian.mapper.UserMapper;
import com.zhixin.mulian.service.IUserService;
import com.zhixin.mulian.utils.Constant;
import com.zhixin.mulian.utils.DateUtils;
import com.zhixin.mulian.utils.RedisTool;
import com.zhixin.mulian.utils.Result;
import com.zhixin.mulian.utils.WXUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
		return Result.ok(list);
	}

	@Override
	public Result<Object> getOpenId(String code) {
		WXLogin login=WXUtils.getOpenId(code);
		//从缓存中查找是否存了openId
		String openId=login.getOpenid();
		User user=(User)redisTool.get(openId);
		//缓存中有取缓存
		if(user!=null) return Result.ok(user);
		//数据库查找
		user=userMapper.selectOne(new QueryWrapper<User>().eq("openid", openId));
		if(user!=null) {
			redisTool.set(openId, user);
			return Result.ok(user);
		}
		user=new User();
		user.setId(UUID.randomUUID().toString());
		user.setBzName(Constant.USERNAME);
		String now=DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		user.setCreateDate(now);
		user.setNickname(Constant.USERNAME);
		user.setOpenid(openId);
		user.setUnionid(login.getUnionid());
		userMapper.insert(user);
		redisTool.set(openId, user);
		return Result.ok(user);
	}
}
