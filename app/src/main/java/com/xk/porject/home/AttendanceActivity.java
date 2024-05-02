package com.xk.porject.home;

import android.Manifest;
import android.graphics.Color;
import android.graphics.Point;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.DotOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityAttendanceBinding;

import java.util.List;

public class AttendanceActivity extends BaseActivityPortrait<ActivityAttendanceBinding> {
    BaiduMap mBaiduMap=null;
    LocationClient mLocationClient=null;
    @Override
    protected void initData() {
        //获取到地图
        mBaiduMap = bind.baiduMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        //设置地图放大的倍数
        init();
        init_location();
        MyLocationConfiguration configuration = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING, true, null);
        mBaiduMap.setMyLocationConfiguration(configuration);
        mBaiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onclick() {
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initPortraitView() {

        PermissionX.init(this).permissions(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION).request(new RequestCallback() {
            @Override
            public void onResult(boolean b, @NonNull List<String> list, @NonNull List<String> list1) {

            }
        });

    }


    @Override
    protected void onResume() {
        bind.baiduMapView.onResume();
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //  addView(bind.baiduMapView);
            }
        });
        super.onResume();
    }
    private static final int paddingBottom = 500;
    private void addView(MapView mapView) {
     TextView   mTextView = new TextView(this);
        mTextView.setTextSize(15.0f);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setBackgroundColor(Color.parseColor("#AAA9A9A9"));
        mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.absoluteMode);
        builder.width(mapView.getWidth());
        builder.height(paddingBottom);
        builder.point(new Point(0, mapView.getHeight()));
        builder.align(MapViewLayoutParams.ALIGN_LEFT, MapViewLayoutParams.ALIGN_BOTTOM);
        mapView.addView(mTextView, builder.build());
    }

    @Override
    protected void onPause() {
        bind.baiduMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.baiduMapView.onDestroy();
        if (mLocationClient != null) {
            // 关闭前台定位服务
            mLocationClient.disableLocInForeground(true);
            // 取消之前注册的 BDAbstractLocationListener 定位监听函数
            if(myLocationListener!=null){
                mLocationClient.unRegisterLocationListener(myLocationListener);
            }
            // 停止定位sdk
            mLocationClient.stop();
        }
    }
    /**
     * 继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            if (ifFrist) {
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll);
                builder.zoom(18.0f);
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                //放大层级
                ifFrist = false;
            }
            mBaiduMap.addOverlay(new CircleOptions().center(ll).
                    radius(200).fillColor(0x00000000).
                    stroke(new Stroke(1, 0x330000FF)));
        }
    }
  boolean  ifFrist =true;
    /**
     *    设置地图放大的倍数
     */
    public  void init(){
        //设置地图放大的倍数
        MapStatus.Builder  builder=new MapStatus.Builder();
        builder.zoom(18f);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    /**
     * 自定义内容:
     * 参数说明
     * (1)定位模式 地图SDK支持三种定位模式：NORMAL（普通态）, FOLLOWING（跟随态）, COMPASS（罗盘态）
     * （2）是否开启方向
     * （3）自定义定位图标 支持自定义定位图标样式，
     * （4）自定义精度圈填充颜色
     * （5）自定义精度圈边框颜色
     */
    public void configure(){

    }
    MyLocationListener myLocationListener;
    /**
     * 定位的初始化
     */
    public void init_location()  {

        mBaiduMap.setMyLocationEnabled(true);
        try {
            LocationClient.setAgreePrivacy(true);
            //定位初始化
            mLocationClient = new LocationClient(this);

//通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//设置locationClientOption
            mLocationClient.setLocOption(option);

//注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
            mLocationClient.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}