package adminproject;

import android.content.Intent;
import android.view.View;


import com.xk.adminproject.databinding.AdActivitySettingBinding;
import com.xk.base.ui.BaseActivityPortrait;

public class SettingActivity extends BaseActivityPortrait<AdActivitySettingBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.tvAddOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this, Ad_add_ManageOilActivity.class);
                startActivity(intent);
            }
        });
        bind.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this, PushPNGActivity.class);
                startActivity(intent);
            }
        });
        bind.tvAdjustManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this, ManageListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {

    }
}