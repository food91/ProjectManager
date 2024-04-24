package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.EmployeeEvaluation;
import com.xk.porject.databinding.ActivityEasorceBinding;
import com.xk.porject.databinding.ItemEaBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class EASorceActivity extends BaseActivityPortrait<ActivityEasorceBinding> {

private CommonAdapter<ItemEaBinding, EmployeeEvaluation> commonAdapter;
private List<EmployeeEvaluation> list=new ArrayList<>();
    @Override
    protected void initData() {
        list.add(new EmployeeEvaluation(true, false, 85, "Engineer"));
        list.add(new EmployeeEvaluation(false, true, 75, "Designer"));
        list.add(new EmployeeEvaluation(true, true, 95, "Manager"));
    }

    @Override
    protected void onclick() {
        commonAdapter =new CommonAdapter<ItemEaBinding, EmployeeEvaluation>(list) {
            @Override
            protected void show(ItemEaBinding holder, int position, EmployeeEvaluation employeeEvaluation) {

            }
        };
        bind.rv.setAdapter(commonAdapter);
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}