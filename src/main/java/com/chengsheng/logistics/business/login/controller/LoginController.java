package com.chengsheng.logistics.business.login.controller;

import com.chengsheng.logistics.business.login.service.LoginService;
import com.chengsheng.logistics.entity.UserEntity;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: logistics->LoginController
 * @description: 登陆Controller
 * @author: Gu Yu Long
 * @date: 2019/09/03 16:52:24
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     *@description  登陆方法
     *@params  [userEntity, req]
     *@return  com.chengsheng.logistics.vo.ServerResponseVo
     *@author  Gu Yu Long
     *@date    2019/9/3 9:31
     *@other
     */
    @RequestMapping("/login")
    public ServerResponseVo login(UserEntity userEntity, HttpServletRequest req){
        userEntity.setLastLoginIp(getIPAddress(req));
        return loginService.login(userEntity);
    }


    /**
     * 获取真实ip
     * getIPAddress
     * @param request
     * @return
     * @return :String
     * @Creator:GU YU LONG
     * @Date:2018年11月26日 下午4:23:51
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
