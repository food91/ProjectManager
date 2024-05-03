package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.ui.excel.Data;

import java.util.ArrayList;
import java.util.List;


public class ProjectScheduleActivity extends BaseActivityPortrait<com.xk.porject.databinding.ActivityProjecttScheduleBinding> {

    List<Data> dataList;
    @Override
    protected void initPortraitView() {


    }
    @Override
    protected void initData() {
        dataList= new ArrayList<>();
        Data data= new Data();
        data.setActualProgress("02/10-04/10  共计：70天");
        data.setText("放线、开挖基台");
        data.setAllProgress("02/10-03/25  完成：65%");
        dataList.add(data);
        Data data2 = new Data();
        data2.setText("搭建钢结构框架");
        data2.setAllProgress("03/26-05/20  共计：60天");
        data2.setActualProgress("03/26-04/20  完成：50%");
        Data data3 = new Data();
        data3.setText("安装玻璃幕墙");
        data3.setAllProgress("05/21-07/10  共计：50天");
        data3.setActualProgress("05/21-06/15  完成：70%");
        dataList.add(data2);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        dataList.add(data3);
        bind.excel.setData(dataList);
    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}