package com.chengsheng.logistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    TEST(1,""),
    USER_EXIST(2,"用户已存在"),
    ADD_USER_ERROR(3,"新增用户失败"),
    ;

    private int code;

    private String message;

}
