package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityProjectCommunicationBinding;
import com.xk.porject.databinding.ItemPcBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProjectCommunicationActivity extends BaseActivityPortrait<ActivityProjectCommunicationBinding> {

CommonAdapter<ItemPcBinding,String> commonAdapter;
List<String> list =new ArrayList<>();

    @Override
    protected void initData() {
        list.add("1");
        list.add("2");
        list.add("3");
    }

    @Override
    protected void onclick() {
        commonAdapter = new CommonAdapter<ItemPcBinding, String>(list) {
            @Override
            protected void show(ItemPcBinding holder, int position, String s) {

            }
        };
        bind.rv.setAdapter(commonAdapter);
        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}