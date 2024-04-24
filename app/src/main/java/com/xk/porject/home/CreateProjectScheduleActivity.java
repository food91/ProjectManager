package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityCreateProjectScheduleBinding;

public class CreateProjectScheduleActivity extends BaseActivityPortrait<ActivityCreateProjectScheduleBinding> {

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
    bind.clCreate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(c, CreateProjectScheduleActivity2.class);
            startActivity(intent);
        }
    });
    }


    @Override
    protected void initPortraitView() {

    }
}