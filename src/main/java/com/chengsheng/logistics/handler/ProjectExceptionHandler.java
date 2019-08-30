package com.chengsheng.logistics.handler;

import com.chengsheng.logistics.exception.ProjectException;
import com.chengsheng.logistics.vo.ServerResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 刘金泳
 * @Date 2019/8/30
 */
@RestControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = ProjectException.class)
    @ResponseStatus(HttpStatus.OK)
    public ServerResponseVo execute(ProjectException e){
        return ServerResponseVo.createByError(e.getMessage());
    }

}
