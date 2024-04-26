package com.xk.base.utils;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class MyData {

    public static String getData(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }
}
