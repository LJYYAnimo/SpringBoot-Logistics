package com.chengsheng.logistics.util;

/**
 * @program: logistics->NumberUtil
 * @description: 数值帮助类
 * @author: Gu Yu Long
 * @date: 2019/09/03 17:45:11
 **/
public class NumberUtil {

    /**
     *@description  将原数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     *@params  [sourceDate, formatLength]
     *@return  java.lang.String
     *@author  Gu Yu Long
     *@date    2019/9/3 17:45
     *@other
     */
    public static String formatNumberWithZero(int sourceDate, int formatLength)
    {
        /*
    　　* 0 指前面补充零
    　　* formatLength 字符总长度为 formatLength
    　　* d 代表为正数。
    　　*/
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

}
