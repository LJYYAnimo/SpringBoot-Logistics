package com.chengsheng.logistics.vo;

import com.chengsheng.logistics.constant.ServerResponseConstant;
import com.chengsheng.logistics.enums.ResultEnum;
import com.chengsheng.logistics.enums.ServerResponseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * 前后端交互相应类
 *
 * @author liujinyong
 *
 * @date 2019/7/9
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ServerResponseVo<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
    private String type;


    ServerResponseVo(Integer code, String type){
        this.code = code;
        this.type = type;
    }

    ServerResponseVo(Integer code, String message, String type){
        this.code = code;
        this.message = message;
        this.type = type;
    }

    ServerResponseVo(Integer code, T data , String type){
        this.code = code;
        this.data = data;
        this.type = type;
    }

    ServerResponseVo(Integer code, String message, T data , String type){
        this.code = code;
        this.message = message;
        this.data = data;
        this.type = type;
    }


    @JsonIgnore
    //不会序列化
    public boolean isSuccess(){
        return this.code.equals(ServerResponseEnum.SUCCESS.getCode());
    }


    public static <T> ServerResponseVo<T> createBySuccess(){
        return new ServerResponseVo<T>(ServerResponseEnum.SUCCESS.getCode(), ServerResponseConstant.SUCCESS);
    }

    public static <T> ServerResponseVo<T> createBySuccess(String message){
        return new ServerResponseVo<T>(ServerResponseEnum.SUCCESS.getCode(),message ,ServerResponseConstant.SUCCESS);
    }

    public static <T> ServerResponseVo<T> createBySuccess(T data){
        return new ServerResponseVo<T>(ServerResponseEnum.SUCCESS.getCode(),data,ServerResponseConstant.SUCCESS);
    }

    public static <T> ServerResponseVo<T> createBySuccess(String message, T data){
        return new ServerResponseVo<T>(ServerResponseEnum.SUCCESS.getCode(),message,data, ServerResponseConstant.SUCCESS);
    }

    public static <T> ServerResponseVo<T> createByError(){
        return new ServerResponseVo<T>(ServerResponseEnum.ERROR.getCode(), ServerResponseEnum.ERROR.getType(),ServerResponseConstant.ERROR);
    }

    public static <T> ServerResponseVo<T> createByError(String message){
        return new ServerResponseVo<T>(ServerResponseEnum.ERROR.getCode(),message,ServerResponseConstant.ERROR);
    }

    public static <T> ServerResponseVo<T> createByError(Integer code , String message){
        return new ServerResponseVo<T>(code,message,ServerResponseConstant.ERROR);
    }

    public static <T> ServerResponseVo<T> createByError(ResultEnum resultEnum){
        return new ServerResponseVo<T>(resultEnum.getCode(),resultEnum.getMessage(),ServerResponseConstant.ERROR);
    }
}
