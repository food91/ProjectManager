package com.xk.porject.home;

import android.content.Intent;
import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityOutInBinding;

public class OutInActivity extends BaseActivityPortrait<ActivityOutInBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.textViewPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, OutInButActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewOutboundOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, OutOrderActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, InventoryActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewReturnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, RetrunOrderActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewMaterialRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, MaterialRatioActivity.class);
                startActivity(intent);
            }
        });
        bind.textViewStatisticalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, RepostStatisActivity.class);
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