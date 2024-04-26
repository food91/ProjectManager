package com.xk.porject.home;


import android.text.TextUtils;
import android.view.View;

import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.AddProjectData;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.databinding.ActivityCreateProjectBinding;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateProjectActivity extends BaseActivityPortrait<ActivityCreateProjectBinding> {


    @Override
    protected void initData() {

    }

    private void tvadd(){
        String name = bind.edName.getText().toString();
        String price = bind.edPrice.getText().toString();
        if(TextUtils.isEmpty(name)){
            PopTip.show("请输入项目名称");
            return;
        }
        if(TextUtils.isEmpty(price)){
            PopTip.show("请输入项目造价");
            return;
        }
        AddProjectData addProjectData =new AddProjectData();
        addProjectData.setProjectName(name);
        addProjectData.setProjectPrice(Integer.parseInt(price));
        addProjectData.setCreateTime(MyData.getData());
        addProjectData.setUpdateTime(MyData.getData());
        ApiClient.getClient().create(ApiService.class).addNewProject(addProjectData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("正在新建项目");
                    }

                    @Override
                    public void onNext(Response response) {
                        WaitDialog.dismiss();
                        if(response.getCode()==200){
                            PopTip.show("创建成功");
                        }else{
                            PopTip.show(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialog.dismiss();
                        e.printStackTrace();
                        PopTip.show("连接失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onclick() {
        bind.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvadd();
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