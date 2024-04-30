package com.xk.porject.home;

import android.view.View;


import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.Response;
import com.xk.base.data.WithdrawaData;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityRetrunOrderBinding;

import io.reactivex.functions.Consumer;

public class RetrunOrderActivity extends BaseActivityPortrait<ActivityRetrunOrderBinding> {


    @Override
    protected void initData() {
        performApiCall(ApiClient.getClient().create(ApiService.class).getpurchaselist(), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                PopTip.show(response.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                PopTip.show("连接失败");
            }
        });
    }

    @Override
    protected void onclick() {
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              send();
            }
        });
    }

    private void send(){
        WithdrawaData withdrawaData = new WithdrawaData();
            performApiCall(ApiClient.getClient().create(ApiService.class).addWithdrawa(withdrawaData), new Consumer<Response>() {
                @Override
                public void accept(Response response) throws Exception {
                    PopTip.show(response.getMsg());
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    PopTip.show("连接失败");
                }
            });
    }

    @Override
    protected void initPortraitView() {

    }
}