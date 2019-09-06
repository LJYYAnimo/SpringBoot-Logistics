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
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PostMapping("/tableList")
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
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

