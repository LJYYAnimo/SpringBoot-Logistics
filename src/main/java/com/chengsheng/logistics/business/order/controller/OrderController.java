package com.chengsheng.logistics.business.order.controller;

import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: logistics->OrderController
 * @description: 订单Controller层
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:51:44
 **/
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
    public ServerResponseVo insert(@RequestBody OrderEntity orderEntity){
        return orderService.insert(orderEntity);
    }

    /**
     *@description  页面表格展示
     *@params  [orderEntity, page, limit]
     *@return  com.chengsheng.logistics.vo.LayuiVo<com.chengsheng.logistics.entity.OrderEntity>
     *@author  Gu Yu Long
     *@date    2019/9/3 17:01
     *@other
     */
    @GetMapping("/tableList")
    public LayuiVo<OrderEntity> tableList(OrderEntity orderEntity,
                                          @RequestParam(value = "page" ,defaultValue = "0") Integer page,
                                          @RequestParam(value = "limit" ,defaultValue = "10") Integer limit){
        return orderService.findList(orderEntity, PageRequest.of(page - 1,limit));
    }

    /**
     *@description  保存订单
     *@params  [orderVo]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/3 17:01
     *@other
     */
    @PostMapping("/save")
    public ServerResponseVo saveOrder(OrderVo orderVo){
        return orderService.saveOrder(orderVo);
    }

}
