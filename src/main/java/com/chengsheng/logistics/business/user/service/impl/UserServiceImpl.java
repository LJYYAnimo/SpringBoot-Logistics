package com.chengsheng.logistics.business.user.service.impl;

import com.chengsheng.logistics.business.user.service.UserService;
import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.enums.ProjectEnum;
import com.chengsheng.logistics.enums.ResultEnum;
import com.chengsheng.logistics.repository.UserEntityRepository;
import com.chengsheng.logistics.util.MD5Util;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author 刘金泳
 * @Date 2019/9/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;


    @Override
    public LayuiVo findList(UserEntity userEntity, Pageable pageable) {
        userEntity.setRemove(ProjectEnum.NOT_DELETE);
        Example<UserEntity> userEntityExample = Example.of(userEntity);
        Page<UserEntity> page = userEntityRepository.findAll(userEntityExample,pageable);
        return new LayuiVo(page.getContent(),page.getTotalElements());
    }

    @Override
    public ServerResponseVo save(UserEntity userEntity) {
        //1、查询用户是否存在
        UserEntity userResult = userEntityRepository.findByUserName(userEntity.getUserName());
        if(userResult != null){
            return ServerResponseVo.createByError(ResultEnum.USER_EXIST);
        }
        //2、密码加密
        userEntity.setPassword(MD5Util.toMD5(userEntity.getPassword()));
        //3、用户存储
        UserEntity saveResult = userEntityRepository.save(userEntity);
        if(saveResult == null){
            return ServerResponseVo.createByError(ResultEnum.ADD_USER_ERROR);
        }
        return ServerResponseVo.createBySuccess();
    }
}
