package com.xk.porject.ui.login;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;


import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.orhanobut.logger.Logger;
import com.xk.base.data.Response;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;

import com.xk.porject.databinding.ActivityForgotPwBinding;
import com.xk.porject.utils.VerificationCodeHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForgotPwActivity extends BaseActivityPortrait<ActivityForgotPwBinding> {

    int time=60;
    VerificationCodeHelper helper;

    private Runnable countdownRunnable;
    @Override
    protected void initData() {
        helper= new VerificationCodeHelper(bind.codeLabel);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.cancelCountdown();
    }


    private void getForgot(){
        String phone = bind.phoneInput.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("手机号码输入不能为空");
            return;
        }
        String code = bind.codeInput.getText().toString();
        if(TextUtils.isEmpty(code)){
            PopTip.show("验证码不能为空");
            return;
        }
        ApiClient.getClient().create(ApiService.class).getForgot(phone,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response s) {
                        X.L(s);
                        if(s.getCode()==200){
                            MessageDialog.show("密码","您的密码是"+s.getData(),"确定").show();
                        }else{
                            PopTip.show("您的密码获取失败");
                            return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            e.printStackTrace();
                            PopTip.show("请求失败");
                            bind.codeLabel.setClickable(true);
                             bind.codeLabel.setText("验证码");
                             time=60;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getCode(){
        String phone = bind.phoneInput.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("请输入手机号");
            return;
        }

       Disposable disposable= helper.requestAndStartCountdown(phone, new ApiClient.ResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                bind.codeInput.setText(response.getData());
            }

            @Override
            public void onError(Throwable throwable) {
                PopTip.show("连接失败");
                throwable.printStackTrace();
            }
        });
       addDisposable(disposable);
    }



    @Override
    protected void onclick() {
        bind.tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForgot();
            }
        });
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