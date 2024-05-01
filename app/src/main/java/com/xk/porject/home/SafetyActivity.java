package com.xk.porject.home;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;

import java.util.ArrayList;
import java.util.List;

public class SafetyActivity extends BaseActivityPortrait<com.xk.porject.databinding.ActivitySafetyBinding> {

    private List<testData> list;
    private List<testData> list2;


    @Override
    protected void initPortraitView() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list2=new ArrayList<>();
        list.add(new testData());
    }

    @Override
    protected void onclick() {
        bind.addRv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()<4){
                    list.add(new testData());
                }
            }
        });
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

    class testData{
        public String tile;
        public boolean ischeck;
    }
}