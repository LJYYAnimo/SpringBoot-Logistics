package com.chengsheng.logistics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 刘金泳
 * @Date 2019/9/2
 */
@Controller
public class ViewController {

    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

}
