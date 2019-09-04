package com.chengsheng.logistics.business.user.service;

import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.data.domain.Pageable;

/**
 * @author liujinyong
 */
public interface UserService {

    /**
     * 分页查询用户
     * @param userEntity
     * @param pageable
     * @return
     */
    LayuiVo findList(UserEntity userEntity, Pageable pageable);

    ServerResponseVo save(UserEntity userEntity);

}
