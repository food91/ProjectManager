package com.xk.base.log;

import com.orhanobut.logger.Logger;

public class X {

    private static boolean debug =true;

    public static void L (Object s){
        if(debug==true){
            Logger.d(s);
        }
    }
}
