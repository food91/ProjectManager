package com.xk.porject.ui;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityElectronicBinding;
import com.xk.porject.databinding.ItemElectronicBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ElectronicActivity extends BaseActivityPortrait<ActivityElectronicBinding> {

    CommonAdapter<ItemElectronicBinding,String> commonAdapter;
    List<String> list=new ArrayList<>();

    @Override
    protected void initData() {
        list.add("1");
        list.add("2");
        list.add("2");
        list.add("2");
        list.add("2");
    }

    @Override
    protected void onclick() {
        commonAdapter = new CommonAdapter<ItemElectronicBinding, String>(list) {
            @Override
            protected void show(ItemElectronicBinding holder, int position, String s) {
                if(position==list.size()-1){
                    holder.clMain.setVisibility(View.GONE);
                    holder.clMain2.setVisibility(View.VISIBLE);
                }
            }
        };
        bind.rv.setAdapter(commonAdapter);
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPortraitView() {
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        bind.rv.setLayoutManager(gridLayoutManager);
    }
}