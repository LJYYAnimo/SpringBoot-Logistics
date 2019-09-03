package com.chengsheng.logistics.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
     * 获取当前时间日历对象
     *
     * @return 返回java.util.Calendar日期对象
     */
    public static Calendar getCurCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 取得当前日期对象
     *
     * @return 返回java.util.Date日期对象
     */
    public static Date getCurDate() {
        return getCurCalendar().getTime();
    }

    /**
     * 获取当前日期时间字符串,格式为 yyyy-MM-dd hh:mm:ss
     *
     * @return 返回当前字符串
     */
    public static String getTodayDate() {
        return getDate(getCurDate(), null);
    }

    /**
     * 获取当前日期时间字符串,格式为 yyyy-MM-dd hh:mm:ss
     *
     * @return 返回当前字符串
     */
    public static String getDatetime() {
        return getDate(getCurDate(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将指定Date类型转换成指定格式的字符串,格式串参见类注释
     * @param date
     * 日期方式
     * @param format
     * 指定的格式,当format为NULL或空串时,<BR>
     * 默认为 yyyy-MM-dd 格式
     * @return 当date为NULL时,返回空串
     */
    public static String getDate(Date date, String format) {

        String dtstr = "";
        if (date == null) {
            return dtstr;
        }

        if (format == null || "".equals(format.trim())) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        dtstr = sdf.format(date);
        return (dtstr == null ? "" : dtstr);

    }

    /**
     * 获取当前时间Timestamp类型
     * @return Timestamp类型当前时间
     */
    public static Timestamp getNowDateTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
