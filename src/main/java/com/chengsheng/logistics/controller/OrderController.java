package com.chengsheng.logistics.controller;

import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.service.OrderService;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/view")
    public List<OrderEntity> view(){
        return orderService.view();
    }

    @RequestMapping(value = "/insert")
    public ServerResponseVo insert(@RequestBody  OrderEntity orderEntity){
        return orderService.insert(orderEntity);
    }

}
