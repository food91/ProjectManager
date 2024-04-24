package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivitySafetyBinding;

public class SafetyActivity extends BaseActivityPortrait<ActivitySafetyBinding> {


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
        bind.tvExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SafetyActivity.this, ExamBamkActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {

    }
}