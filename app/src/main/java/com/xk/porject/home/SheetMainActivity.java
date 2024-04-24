package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivitySheetMainBinding;

public class SheetMainActivity extends BaseActivityPortrait<ActivitySheetMainBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.tvM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =new Intent(c, ProjectCommunicationActivity.class);
                startActivity(intent);
            }
        });
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