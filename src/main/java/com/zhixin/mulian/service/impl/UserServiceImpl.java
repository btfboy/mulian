package com.zhixin.mulian.service.impl;

import com.zhixin.mulian.entity.User;
import com.zhixin.mulian.mapper.UserMapper;
import com.zhixin.mulian.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
