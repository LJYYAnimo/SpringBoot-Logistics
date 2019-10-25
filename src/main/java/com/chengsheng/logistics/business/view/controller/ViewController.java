package com.chengsheng.logistics.business.view.controller;

import com.chengsheng.logistics.business.order.service.OrderService;
import com.chengsheng.logistics.business.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: logistics->ViewController
 * @description: 视图控制器
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:53:25
 **/
@Controller
public class ViewController {

    @Autowired
    private OrderService orderService;

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

    /**
     * 主体页面
     * @return
     */
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    /**
     *@description  订单展示
     *@params
     *@return
     *@author  Gu Yu Long
     *@date    2019/9/3 9:30
     *@other
     */
    @GetMapping("/home/order/list")
    public String homeTableOrderList(){
        return "order/tableList";
    }

    /**
     * 用户列表
     * @return
     */
    @GetMapping("/home/user/list")
    public String homeTableUserList(){
        return "user/tableList";
    }


    /**
     *@description  新增页面展示
     *@params  []
     *@return  java.lang.String
     *@author  Gu Yu Long
     *@date    2019/9/3 9:31
     *@other
     */
    @GetMapping("/home/order/edit")
    public String homeEditOrder(){
        return "order/edit";
    }

    /**
     * 用户编辑展示页面
     * @return
     */
    @GetMapping("/home/user/edit")
    public String homeEditUser(){
        return "user/edit";
    }


    /**
     * 编辑页面展示
     * @return
     */
    @GetMapping("/home/order/editDetail")
    public ModelAndView homeEditDetailOrder(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        OrderVo result = orderService.getOrderInfoById(id);
        modelMap.put("result",result);
        return new ModelAndView("order/editDetail").addAllObjects(modelMap);
    }

    /**
     * 支付页面展示
     * @return
     */
    @GetMapping("/home/order/pay")
    public ModelAndView homeOrderPay(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        OrderVo result = orderService.getOrderPayInfoById(id);
        modelMap.put("result",result);
        return new ModelAndView("order/pay").addAllObjects(modelMap);
    }
}
