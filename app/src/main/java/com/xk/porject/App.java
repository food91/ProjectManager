package com.xk.porject;

import android.app.Activity;
import android.app.Application;


import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.kongzue.dialogx.DialogX;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import java.util.Locale;


public class App extends Application {

    public static Application instance =null;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        DialogX.init(this);
        SDKInitializer.setAgreePrivacy(this,true);
        SDKInitializer.initialize(this);
        instance = this;
    }
}
