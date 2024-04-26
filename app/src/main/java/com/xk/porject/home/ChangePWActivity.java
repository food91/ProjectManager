package com.xk.porject.home;

import android.text.TextUtils;
import android.view.View;


import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivity;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityChangePwactivityBinding;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePWActivity extends BaseActivityPortrait<ActivityChangePwactivityBinding> {

    @Override
    protected void initPortraitView() {

    }

    @Override
    protected void initData() {

    }

    private void commoit(){
        String old_pw = bind.edOldPw.getText().toString();
        String new_pw = bind.edNewPw.getText().toString();
        String new_pw2 = bind.edNewPw2.getText().toString();
        if(TextUtils.isEmpty(old_pw)||TextUtils.isEmpty(new_pw)){
            PopTip.show("请填写密码");
            return;
        }
        if(!new_pw.equals(new_pw2)){
            PopTip.show("两次输入新密码不相等");
            return;
        }
        ApiClient.getClient().create(ApiService.class).UpdatePW(old_pw,new_pw,new_pw2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("发送中...");
                    }

                    @Override
                    public void onNext(Response response) {
                        WaitDialog.dismiss();
                        if(response.getCode()==200){
                            PopTip.show("密码修改成功");
                        }else{
                            PopTip.show(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onclick() {
        bind.tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commoit();
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}