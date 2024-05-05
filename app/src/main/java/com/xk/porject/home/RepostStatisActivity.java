package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.ui.excel.Data;
import com.xk.porject.databinding.ActivityRepostStatisBinding;

import java.util.ArrayList;
import java.util.List;

public class RepostStatisActivity extends BaseActivityPortrait<ActivityRepostStatisBinding> {

    private List<String> name;
    @Override
    protected void initData() {
        name = new ArrayList<>();
        name.add("按材料");
        name.add("全部");
        bind.spName.attachDataSource(name);
        List<String> headstr = new ArrayList<>();
        headstr.add("时间");
        headstr.add("入库");
        headstr.add("出库");
        headstr.add("结余");
        bind.excel.setHeadData(headstr);
        List<Data> data =new ArrayList<>();
        Data data1 =new Data();
        List<String> body = new ArrayList<>();
        body.add("2023-3-5");
        body.add("Φ16螺纹");
        body.add("15");
        body.add("11");
        data1.setRows(body);
        data.add(data1);
        data.add(data1);
        data.add(data1);
        data.add(data1);
        bind.excel.setData(data);
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

    }
}