package adminproject;

import android.view.View;

import com.xk.adminproject.databinding.ActivityAdAddoilCodeBinding;
import com.xk.base.ui.BaseActivityPortrait;

public class Ad_AddoilCodeActivity extends BaseActivityPortrait<ActivityAdAddoilCodeBinding> {


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