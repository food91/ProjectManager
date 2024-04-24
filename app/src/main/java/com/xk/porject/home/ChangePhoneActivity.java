package com.xk.porject.home;

import android.view.View;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityChangePhoneBinding;

public class ChangePhoneActivity extends BaseActivityPortrait<ActivityChangePhoneBinding> {


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

    @Override
    protected void initPortraitView() {

    }
}