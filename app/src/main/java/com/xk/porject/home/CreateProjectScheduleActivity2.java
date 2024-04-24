package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityCreateProjectSchedule2Binding;

public class CreateProjectScheduleActivity2 extends BaseActivityPortrait<ActivityCreateProjectSchedule2Binding> {

    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.clCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, CreateSinglePlanActivity.class);
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