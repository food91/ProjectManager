package adminproject;

import android.view.View;


import com.xk.adminproject.databinding.AdActivityPushPngactivityBinding;
import com.xk.base.ui.BaseActivityPortrait;

public class PushPNGActivity extends BaseActivityPortrait<AdActivityPushPngactivityBinding> {

    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
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