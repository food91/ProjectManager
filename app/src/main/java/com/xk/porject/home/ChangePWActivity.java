package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivity;
import com.xk.porject.databinding.ActivityChangePwactivityBinding;

public class ChangePWActivity extends BaseActivity<ActivityChangePwactivityBinding> {

    @Override
    protected void initView() {

    }

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
    }

}