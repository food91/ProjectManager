package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityAdvancedMaterialFormBinding;

//备料单
public class AdvancedMaterialFormActivity extends BaseActivityPortrait<ActivityAdvancedMaterialFormBinding> {


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
        bind.submitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, MaterialHistoryActivity.class);
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