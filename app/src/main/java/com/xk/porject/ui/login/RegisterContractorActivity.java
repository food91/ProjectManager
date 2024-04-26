package com.xk.porject.ui.login;





import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;


import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.orhanobut.logger.Logger;
import com.xk.base.data.Identity;
import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityContractorBinding;
import com.xk.porject.utils.VerificationCodeHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterContractorActivity extends BaseActivityPortrait<ActivityContractorBinding> {
    VerificationCodeHelper helper;

    @Override
    protected void initData() {
        helper= new VerificationCodeHelper(bind.tvCodeLabel);
    }
    private void getcode(){
        String phone = bind.edManagephone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("请输入管理员手机号");
            return;
        }
        Disposable disposable= helper.requestAndStartCountdown(phone, new ApiClient.ResponseCallback() {
            @Override
            public void onSuccess(Response response) {
                bind.edCode.setText(response.getData());
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
    protected void onDestroy() {
        super.onDestroy();
        helper.cancelCountdown();
    }
    private void addContract(){
        String name = bind.edName.getText().toString();
        String phone = bind.edManagephone.getText().toString();
        if(TextUtils.isEmpty(name)){
            PopTip.show("请输入公司名称");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            PopTip.show("请输入管理员手机号");
            return;
        }
        List<ProjectPartyData> list = new ArrayList<>();
        ProjectPartyData  projectPartyData =new ProjectPartyData();
        projectPartyData.setCompany(name);
        projectPartyData.setAdminPhone(phone);
        list.add(projectPartyData);
        ApiClient.getClient().create(ApiService.class).PostProjectPartyData(
                list, Identity.CONTRACTOR_PARTY_ID,Identity.CODE
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("注册中...");
                    }

                    @Override
                    public void onNext(Response response) {
                        WaitDialog.dismiss();
                        if(response.getCode()==200){
                            PopTip.show("注册成功");

                        }else{
                            PopTip.show("注册失败"+response.getMsg());
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

        bind.ivCop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addContract();
            }
        });
        bind.tvCodeLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcode();
            }
        });
        bind.ivCop.setOnClickListener(new View.OnClickListener() {
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