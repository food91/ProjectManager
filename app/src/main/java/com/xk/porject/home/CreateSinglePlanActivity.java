package com.xk.porject.home;

import android.view.View;

import com.kongzue.dialogx.datepicker.CalendarDialog;
import com.kongzue.dialogx.datepicker.interfaces.OnDateSelected;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityCreateSinglePlanBinding;

public class CreateSinglePlanActivity extends BaseActivityPortrait<ActivityCreateSinglePlanBinding> {


    @Override
    protected void initData() {

    }

    void showDataPicket(){
        CalendarDialog.build()
                .show(new OnDateSelected() {
                    /**
                     * 此处为回调，
                     * @param text 直接返回默认文本，例如“2021-9-25”
                     * @param year  年
                     * @param month 月
                     * @param day   日
                     */
                    @Override
                    public void onSelect(String text, int year, int month, int day) {

                    }
                });
    }

    @Override
    protected void onclick() {
        bind.flT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPicket();
            }
        });
        bind.flT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPicket();
            }
        });
        bind.flT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPicket();
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