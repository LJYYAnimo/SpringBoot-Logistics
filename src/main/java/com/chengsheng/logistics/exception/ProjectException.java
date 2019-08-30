package com.chengsheng.logistics.exception;

import com.chengsheng.logistics.enums.ResultEnum;
import lombok.Getter;

/**
 * @author 刘金泳
 * @Date 2019/8/30
 */
@Getter
public class ProjectException extends RuntimeException{

    private Integer code;

    public ProjectException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ProjectException(Integer code,String message) {
        super(message);
        this.code = code;
    }


}
