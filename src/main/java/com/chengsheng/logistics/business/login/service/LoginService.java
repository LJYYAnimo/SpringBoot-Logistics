package com.chengsheng.logistics.business.login.service;

import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.vo.ServerResponseVo;

/**
 * @program: logistics->LoginService
 * @description: 登陆Service层
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:53:03
 **/
public interface LoginService {
    ServerResponseVo login(UserEntity userEntity);
}