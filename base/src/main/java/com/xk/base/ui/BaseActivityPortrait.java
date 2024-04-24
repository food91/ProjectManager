package com.xk.base.ui;

import android.content.pm.ActivityInfo;

import androidx.viewbinding.ViewBinding;

import com.orhanobut.logger.Logger;


public abstract class BaseActivityPortrait<T extends ViewBinding> extends BaseActivity<T> {

    protected abstract void initPortraitView();
    @Override
    protected void initView() {
        Logger.d("--------------------------"+getLocalClassName());
        setEx();
        initPortraitView();

    }
    private void setEx(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
