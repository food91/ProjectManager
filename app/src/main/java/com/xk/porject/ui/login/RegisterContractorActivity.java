package com.xk.porject.ui.login;





import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;


import com.kongzue.dialogx.dialogs.PopTip;
import com.orhanobut.logger.Logger;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityContractorBinding;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterContractorActivity extends BaseActivityPortrait<ActivityContractorBinding> {


    @Override
    protected void initData() {

    }
    private void addContract(){
        PopTip.show("addContract");

    }

    private void getCode(){

        String phone = bind.edManagephone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("验证码不能为空");
            return;
        }
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getCode(phone).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response s) {
                        Logger.d("s=="+s);
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
        bind.tvCodeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        bind.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContract();
            }
        });
    bind.ivBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }

    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}