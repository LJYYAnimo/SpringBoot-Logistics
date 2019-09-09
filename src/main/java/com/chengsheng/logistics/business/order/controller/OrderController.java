package com.chengsheng.logistics.business.order.controller;

import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import com.chengsheng.logistics.entity.OrderEntity;
import com.chengsheng.logistics.vo.LayuiVo;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public LayuiVo<OrderEntity> tableList(OrderEntity orderEntity, Integer page, Integer limit){
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
    public ServerResponseVo saveOrder(@RequestBody OrderVo orderVo){
        return orderService.saveOrder(orderVo);
    }


    /***
     *@description  删除订单
     *@params  [orderVo]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/6 8:56
     *@other
     */
    @PostMapping("/delete")
    public ServerResponseVo delete(@RequestBody OrderEntity order){
        return orderService.delete(order);
    }

    /***
     *@description  导出客户excel结算单
     *@params  [order, response]
     *@return  void
     *@author  Gu Yu Long
     *@date    2019/9/4 15:39
     *@other
     */
    @GetMapping("/export")
    public void exportForExcel(@ModelAttribute OrderEntity order, HttpServletResponse response){
        orderService.exportForExcel(order, response);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

