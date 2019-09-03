package com.chengsheng.logistics.business.login.service.impl;

import com.chengsheng.logistics.business.login.service.LoginService;
import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.repository.UserEntityRepository;
import com.chengsheng.logistics.util.DateUtil;
import com.chengsheng.logistics.util.MD5Util;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: logistics->LoginServiceImpl
 * @description: 登陆实现层
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:54:42
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public ServerResponseVo login(UserEntity userEntity) {
        try {
            UserEntity user = userEntityRepository.findByUserName(userEntity.getUserName());
            if(user == null){
                return ServerResponseVo.createByError("用户不存在");
            }
            if(!user.getPassword().equals(MD5Util.toMD5(userEntity.getPassword()))){
                return ServerResponseVo.createByError("密码不正确");
            }
            if(!user.getStatus().equals(1)){
                return ServerResponseVo.createByError("用户未激活");
            }
            user.setLastLoginIp(userEntity.getLastLoginIp());
            user.setLastLoginTime(DateUtil.getNowDateTimeStamp());
            userEntityRepository.save(user);
            return ServerResponseVo.createBySuccess();
        }catch (Exception e){
            return ServerResponseVo.createByError("登陆出错，请联系管理员");
        }
    }
}
