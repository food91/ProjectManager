package com.xk.porject.home;

import android.view.View;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityMaterialRatioBinding;

import java.util.ArrayList;
import java.util.List;

public class MaterialRatioActivity extends BaseActivityPortrait<ActivityMaterialRatioBinding> {

    private List<String> materialList;

    @Override
    protected void initData() {
        materialList = new ArrayList<>();
        materialList.add("C15#");
        materialList.add("C25#");
        materialList.add("C35#");
        materialList.add("C45#");
        materialList.add("砂浆");
        materialList.add("垫层");
        materialList.add("灌浆");
        materialList.add("线塔");
        materialList.add("基座");
        bind.spinner.attachDataSource(materialList);
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