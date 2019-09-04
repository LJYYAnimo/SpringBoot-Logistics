package com.chengsheng.logistics.business.user.controller;

import com.chengsheng.logistics.business.user.service.UserService;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 刘金泳
 * @Date 2019/9/4
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/tableList")
    public LayuiVo<OrderEntity> tableList(UserEntity userEntity,
                                          @RequestParam(value = "page" ,defaultValue = "0") Integer page,
                                          @RequestParam(value = "limit" ,defaultValue = "10") Integer limit){
        return userService.findList(userEntity, PageRequest.of(page - 1,limit));
    }

    @PostMapping("save")
    public ServerResponseVo save(UserEntity userEntity){
//        return userService.save(userEntity);
        return null;
    }

}
