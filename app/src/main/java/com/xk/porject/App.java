package com.xk.porject;

import android.app.Activity;
import android.app.Application;


import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.kongzue.dialogx.DialogX;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mmkv.MMKV;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class App extends Application {

    public static Application instance =null;
    public static int  ID = 0;

    public  static List<HashMap<String,String>> hashMapList;



    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(2)               // （可选）要显示的方法行数。 默认2
                .tag("MyTAG")                  //（可选）每个日志的全局标记。 默认PRETTY_LOGGER（如上图）
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        DialogX.init(this);
        SDKInitializer.setAgreePrivacy(this,true);
        SDKInitializer.initialize(this);
        MMKV.initialize(this);
        instance = this;
    }
}
