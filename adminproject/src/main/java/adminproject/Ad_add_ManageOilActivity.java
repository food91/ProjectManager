package adminproject;

import android.content.Intent;
import android.view.View;

import com.xk.adminproject.databinding.ActivityAdAddManageOilBinding;
import com.xk.base.ui.BaseActivityPortrait;

public class Ad_add_ManageOilActivity extends BaseActivityPortrait<ActivityAdAddManageOilBinding> {


    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.tvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, Ad_AddoilCodeActivity.class);
                startActivity(intent);
            }
        });
        bind.back.setOnClickListener(new View.OnClickListener() {
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