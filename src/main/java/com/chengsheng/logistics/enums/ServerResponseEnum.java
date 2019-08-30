package com.chengsheng.logistics.enums;

/**
 * 消息响应
 *
 * @author liujinyong
 *
 * @date 2019/7/9
 */
public enum ServerResponseEnum {
    /**
     * 0 成功
     */
    SUCCESS(0,"SUCCESS"),
    /**
     * 1 失败
     */
    ERROR(1,"ERROR");

    private Integer code;
    private String type;

    ServerResponseEnum(Integer code, String type){
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
