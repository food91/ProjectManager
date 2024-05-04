package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
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