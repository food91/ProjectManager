package com.xk.porject.utils;

import android.icu.text.SimpleDateFormat;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static final int WORK_STATE_ON_THE_JOB = 134;
    public static final int WORK_STATE_ON_OUT_THE_JOB = 135;

    public static String formatDateWithDefaultTime(String originalDateString) {
        // 原始日期的格式（注意：MM是月份，mm是分钟）
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-M-dd", Locale.getDefault());

        // 目标日期的格式，包含时间部分
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        try {
            // 解析原始日期字符串
            Date date = inputFormat.parse(originalDateString);

            // 使用输出格式格式化日期，这将自动添加默认的时间部分（因为我们没有设置时间）
            String formattedDate = outputFormat.format(date);

            return formattedDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 或者返回一个错误消息字符串
        }
    }


    public static boolean areAllStringsValid(String... strings) {
        if (strings == null) {
            return false; // 如果没有传递任何字符串（理论上不应该发生，因为至少是 null），返回 false
        }

        for (String str : strings) {
            if (TextUtils.isEmpty(str)) {
                return false; // 找到了一个空或只包含空白字符的字符串，返回 false
            }
        }

        return true; // 所有字符串都通过了检查，返回 true
    }

    public static  int getWorkState(int spinnerInt){
        if(spinnerInt==0){
            return WORK_STATE_ON_THE_JOB;
        }else{
            return WORK_STATE_ON_OUT_THE_JOB;
        }
    }

    public static String getWorkState_str(int workstate){
        if(WORK_STATE_ON_THE_JOB==workstate){
            return "当前状态：在职";
        }else{
            return "当前状态：离职";
        }
    }

    public static int getAllDayMon(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return daysInMonth;
    }

    public static int getWeekDay(int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 获取星期几，Calendar.DAY_OF_WEEK 会返回一个整数值，代表星期几
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekday = 1;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                weekday =7;
                break;
            case Calendar.MONDAY:
                weekday = 1;
                break;
            case Calendar.TUESDAY:
                weekday = 2;
                break;
            case Calendar.WEDNESDAY:
                weekday = 3;
                break;
            case Calendar.THURSDAY:
                weekday = 4;
                break;
            case Calendar.FRIDAY:
                weekday = 5;
                break;
            case Calendar.SATURDAY:
                weekday = 6;
                break;
        }
        return weekday;
    }
}
