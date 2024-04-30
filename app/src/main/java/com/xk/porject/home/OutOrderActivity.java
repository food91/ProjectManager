package com.xk.porject.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityOutOrderBinding;
import com.xk.porject.databinding.ItemOoaBinding;

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
        bind.tvQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c,InventoryActivity.class);
                startActivity(intent);
            }
        });
        bind.tvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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