package adminproject;

import android.view.View;


import com.xk.adminproject.databinding.AdActivityAddManageListBinding;
import com.xk.base.ui.BaseActivityPortrait;


public class AddManageListActivity extends BaseActivityPortrait<AdActivityAddManageListBinding> {

    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
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