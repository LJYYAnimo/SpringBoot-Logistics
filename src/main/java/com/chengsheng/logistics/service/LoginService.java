package com.chengsheng.logistics.service;

import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.vo.ServerResponseVo;

public interface LoginService {
    ServerResponseVo login(UserEntity userEntity);
}
