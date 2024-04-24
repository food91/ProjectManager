package com.xk.porject.utils;

import java.util.Calendar;

public class Utils {

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
