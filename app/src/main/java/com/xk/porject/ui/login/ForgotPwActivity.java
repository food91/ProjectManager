package com.xk.porject.ui.login;

import android.text.TextUtils;
import android.view.View;


import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.orhanobut.logger.Logger;
import com.xk.base.data.Response;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;

import com.xk.porject.databinding.ActivityForgotPwBinding;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgotPwActivity extends BaseActivityPortrait<ActivityForgotPwBinding> {


    @Override
    protected void initData() {

    }

    private void getCode(){
        String phone = bind.phoneInput.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("手机号码输入不能为空");
            return;
        }
        ApiClient.getClient().create(ApiService.class).getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response s) {
                        X.L(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                            e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onclick() {
        bind.codeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getCode();
            }
        });
        bind.backButton.setOnClickListener(new View.OnClickListener() {
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