package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityInventoryBinding;

public class InventoryActivity extends BaseActivityPortrait<ActivityInventoryBinding> {



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
    }

    @Override
    protected void initPortraitView() {

    }
}