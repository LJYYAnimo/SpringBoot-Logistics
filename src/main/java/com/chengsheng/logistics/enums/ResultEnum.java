package com.chengsheng.logistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    TEST(1,""),;

    private int code;

    private String message;

}
