package com.xk.porject.ui.login;


import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProvider;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityProjectRegisterBinding;
import com.xk.porject.viewmodel.ProjectRegisterViewModel;

public class ProjectRegisterActivity extends BaseActivityPortrait<ActivityProjectRegisterBinding> {
    ProjectRegisterViewModel vm =  new ViewModelProvider(this).get(ProjectRegisterViewModel.class);

    @Override
    protected void initData() {

    }



    @Override
    protected void onclick() {
        bind.ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bind.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewToLayout(bind.llMid2);
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addViewToLayout(LinearLayout parentLayout) {
        // 实例化新布局
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        // 可以对新布局的视图元素进行配置，例如设置文本等
        // 将新布局添加到父布局中
        parentLayout.addView(itemView);
    }

    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}