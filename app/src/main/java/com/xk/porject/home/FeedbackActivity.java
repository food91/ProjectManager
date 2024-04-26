package com.xk.porject.home;

import android.text.TextUtils;
import android.view.View;


import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.FeedData;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.databinding.ActivityFeedbackBinding;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FeedbackActivity extends BaseActivityPortrait<ActivityFeedbackBinding> {

    @Override
    protected void initData() {

    }

    private void send(){
        String msg = bind.edMsg.getText().toString();
        String finder = bind.edFinder.getText().toString();
        if(TextUtils.isEmpty(msg)){
            PopTip.show("请填写意见");
            return;
        }
        if(TextUtils.isEmpty(finder)){
            PopTip.show("请填写联系人");
            return;
        }
        FeedData feedData = new FeedData();
        feedData.setCreateTime(MyData.getData());
        feedData.setUpdateTime(MyData.getData());
        feedData.setFeedbackContent(msg);
        feedData.setFeedbackNumber(finder);
        feedData.setFeedbackTime(MyData.getData());
        ApiClient.getClient().create(ApiService.class).SendFeedBack(feedData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("正在发送...");
                    }

                    @Override
                    public void onNext(Response response) {
                        WaitDialog.dismiss();
                        if(response.getCode()==200){
                            PopTip.show("发送成功");
                        }else{
                            PopTip.show("发送失败"+response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onclick() {
        bind.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
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

    }
}