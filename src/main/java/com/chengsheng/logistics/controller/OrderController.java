package com.chengsheng.logistics.controller;

import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.service.OrderService;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liujinyong
 */
@RestController
@RequestMapping("/order/")
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

    @GetMapping("/tableList")
    public LayuiVo<OrderEntity> tableList(OrderEntity orderEntity,
                                          @RequestParam(value = "page" ,defaultValue = "0") Integer page,
                                          @RequestParam(value = "limit" ,defaultValue = "10") Integer limit){
        return orderService.findList(orderEntity, PageRequest.of(page - 1,limit));
    }

}
