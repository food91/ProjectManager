package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityRetrunOrderBinding;

public class RetrunOrderActivity extends BaseActivityPortrait<ActivityRetrunOrderBinding> {


    @Override
    protected void initData() {

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