package com.chengsheng.logistics.util;

import java.sql.Timestamp;

public class DateUtil {
    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }

    /**
     * 获取当前时间Timestamp类型
     * @return Timestamp类型当前时间
     */
    public static Timestamp getNowDateTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
