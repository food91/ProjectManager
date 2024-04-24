package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityJobMarketBinding;
import com.xk.porject.databinding.ItemJbomarketBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class JobMarketActivity extends BaseActivityPortrait<ActivityJobMarketBinding> {

    CommonAdapter<ItemJbomarketBinding,String> commonAdapter;


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<String> data =new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        commonAdapter = new CommonAdapter<ItemJbomarketBinding, String>(data) {
            @Override
            protected void show(ItemJbomarketBinding holder, int position, String s) {

            }
        };
        bind.rv.setAdapter(commonAdapter);
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}