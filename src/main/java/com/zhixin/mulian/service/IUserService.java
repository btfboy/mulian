package com.zhixin.mulian.service;

import com.zhixin.mulian.entity.User;
import com.zhixin.mulian.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-05-28
 */
public interface IUserService extends IService<User> {

	Result<Object> getAllUser();

	Result<Object> getOpenId(String code);

}
