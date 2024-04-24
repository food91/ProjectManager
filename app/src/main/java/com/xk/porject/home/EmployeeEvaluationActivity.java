package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.EaData;
import com.xk.porject.databinding.ActivityEmployeeEvaluationBinding;
import com.xk.porject.databinding.ItemEa2Binding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeEvaluationActivity extends BaseActivityPortrait<ActivityEmployeeEvaluationBinding> {

    CommonAdapter<ItemEa2Binding, EaData> commonAdapter;
    List<EaData> list=new ArrayList<>();

    @Override
    protected void initData() {
        list.add(new EaData("张三", "文案工作", "文案类"));
        list.add(new EaData("李四", "设计工作", "设计类"));
        list.add(new EaData("王五", "开发工作", "开发类"));
        list.add(new EaData("赵六", "测试工作", "测试类"));
        list.add(new EaData("孙七", "运营工作", "运营类"));
        list.add(new EaData("周八", "管理工作", "管理类"));

    }

    @Override
    protected void onclick() {
        commonAdapter =new CommonAdapter<ItemEa2Binding, EaData>(list) {
            @Override
            protected void show(ItemEa2Binding holder, int position, EaData eaData) {
                holder.iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.iv2.getVisibility()==View.GONE){
                            holder.iv1.setAlpha(0.2f);
                            holder.iv2.setVisibility(View.VISIBLE);
                        }else{
                            holder.iv1.setAlpha(1.0f);
                            holder.iv2.setVisibility(View.GONE);
                        }

                    }
                });
            }
        };
        bind.rv.setAdapter(commonAdapter);
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPortraitView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        bind.rv.setLayoutManager(layoutManager);
    }
}