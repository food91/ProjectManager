package com.xk.porject.ui.login;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivityPortrait<ActivityRegisterBinding> {


    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        bind.edProjectvip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this, ProjectRegisterActivity.class);
                startActivity(intent);
            }
        });
        bind.edSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this, RegisterContractorActivity.class);
                startActivity(intent);
            }
        });
        bind.edContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this, RegisterContractorActivity.class);
                startActivity(intent);
            }
        });
    }
}