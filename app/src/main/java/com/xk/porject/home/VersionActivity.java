package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityVersionBinding;

public class VersionActivity extends BaseActivityPortrait<ActivityVersionBinding> {

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
        bind.tvUserask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(VersionActivity.this, StatementActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {

    }
}