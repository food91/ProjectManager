package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;

import com.xk.porject.data.UserData;
import com.xk.porject.databinding.ActivitySwitchAccountsBinding;
import com.xk.porject.databinding.ItemAccontSwitchBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwitchAccountsActivity extends BaseActivityPortrait<ActivitySwitchAccountsBinding> {

    private CommonAdapter<ItemAccontSwitchBinding, UserData> commonAdapter;
    private List<UserData> list;
    @Override
    protected void initData() {
        list=new ArrayList<>();
        // 示例数据1
        UserData userData1 = new UserData(
                R.drawable.head, // 头像资源ID
                "软件工程师\n张三", // 职位和姓名
                "+86 12345678901", // 电话号码
                R.drawable.project // 假设这是项目图标的资源ID
        );

// 示例数据2
        UserData userData2 = new UserData(
                R.drawable.head, // 头像资源ID
                "产品经理\n李四", // 职位和姓名
                "+86 23456789012", // 电话号码
                R.drawable.project // 项目图标资源ID
        );

// 示例数据3
        UserData userData3 = new UserData(
                R.drawable.head, // 头像资源ID
                "UI设计师\n王五", // 职位和姓名
                "+86 34567890123", // 电话号码
                R.drawable.project // 项目图标资源ID
        );
        list.add(userData1);
        list.add(userData2);
        list.add(userData3);
    }

    @Override
    protected void onclick() {
        commonAdapter =new CommonAdapter<ItemAccontSwitchBinding, UserData>(list) {
            @Override
            protected void show(ItemAccontSwitchBinding holder, int position, UserData userData) {
                holder.tvUserTitle.setText(userData.getTitle());

                // 设置电话号码
                holder.tvUserPhone.setText(userData.getPhoneNumber());
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