package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityOutOrderBinding;
import com.xk.porject.databinding.ItemOoaBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class OutOrderActivity extends BaseActivityPortrait<ActivityOutOrderBinding> {

    CommonAdapter<ItemOoaBinding,String> commonAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void initData() {
        list.add("1");
        list.add("2");

    }

    @Override
    protected void onclick() {
        commonAdapter= new CommonAdapter<ItemOoaBinding, String>(list) {
            @Override
            protected void show(ItemOoaBinding holder, int position, String s) {

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
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}