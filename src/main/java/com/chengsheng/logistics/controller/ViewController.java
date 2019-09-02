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

    @GetMapping("/home/order/list")
    public String homeTableOrderList(){
        return "order/tableList";
    }

}
