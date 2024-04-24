package com.xk.porject.home;

import android.content.Intent;
import android.view.View;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.SignaTureActivity;

import com.xk.porject.databinding.ActivityProgressBinding;
import com.xk.porject.ui.ElectronicActivity;

public class ProgressActivity extends BaseActivityPortrait<ActivityProgressBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.textViewSignatureTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, SignaTureActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewElectronicSealLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, ElectronicActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewCreateProgressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, CreateSinglePlanActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewCreateProjectSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, CreateProjectScheduleActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewCreateContactSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, SheetMainActivity.class);
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