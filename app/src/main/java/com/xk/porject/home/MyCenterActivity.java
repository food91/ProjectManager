package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityMyCenterBinding;

public class MyCenterActivity extends BaseActivityPortrait<ActivityMyCenterBinding> {

    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, AdvancedMaterialFormActivity.class);
                startActivity(intent);
            }
        });
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