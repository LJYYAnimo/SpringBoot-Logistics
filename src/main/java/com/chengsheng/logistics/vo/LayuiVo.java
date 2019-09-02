package com.chengsheng.logistics.vo;

import lombok.Data;

/**
 * LayUI表格分页参数
 *
 * @author liujinyong
 *
 * @date 2019/7/9
 */
@Data
public class LayuiVo<T> {

    private Integer code = 0;

    private long count;

    private T data;

    private String msg ;

    public LayuiVo(){

    }

    public LayuiVo(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }

    public LayuiVo(T data, long count) {
        this.count = count;
        this.data = data;
    }
}

