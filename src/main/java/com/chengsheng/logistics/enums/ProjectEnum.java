package com.chengsheng.logistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 刘金泳
 * @Date 2019/8/30
 */
@Getter
@AllArgsConstructor
public enum ProjectEnum {

    NOT_DELETE(0,"未删除"),
    YEW_DELETE(1,"已删除"),
    NOT_PAY(2, "未支付"),
    PAY_SOME(3, "部分支付"),
    PAY_ALL(4, "已结清");

    private int code;

    private String value;

    public static ProjectEnum getByCode(int code){
        for(ProjectEnum enums : ProjectEnum.values()){
            if(enums.getCode() == code){
                return enums;
            }
        }
        return null;
    }

}
