package com.chengsheng.logistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 刘金泳
 * @Date 2019/9/2
 */
@Controller
public class ViewController {

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
     *@description  编辑页面展示
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

}
