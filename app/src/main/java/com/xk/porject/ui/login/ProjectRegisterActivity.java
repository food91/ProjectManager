package com.xk.porject.ui.login;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProvider;

import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.ProjectRegisterData;
import com.xk.base.data.Response;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityProjectRegisterBinding;
import com.xk.porject.utils.VerificationCodeHelper;
import com.xk.porject.viewmodel.ProjectRegisterViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectRegisterActivity extends BaseActivityPortrait<ActivityProjectRegisterBinding> {


    private List<EditText> editTexts;
    VerificationCodeHelper helper;

    @Override
    protected void initData() {
        helper= new VerificationCodeHelper(bind.tvGetCode);
        editTexts =new ArrayList<>();
    }

    private void registerSubmit(){
        List<ProjectPartyData> list = new ArrayList<>();
        ProjectPartyData  projectPartyData =new ProjectPartyData();
        projectPartyData.setCompany(bind.textInputEditTextCompanyName.getText().toString());
        projectPartyData.setProject(bind.textInputEditTextProjectName.getText().toString());
        projectPartyData.setSuccessful(bind.textInputEditTextWinningUnit.getText().toString());
        projectPartyData.setSection(bind.textInputEditTextLot.getText().toString());
        projectPartyData.setBiddingPrice(Integer.parseInt(bind.textInputEditTextWinningPrice.getText().toString()));
        projectPartyData.setPhone(bind.textInputEditTextPhone.getText().toString());
        projectPartyData.setAdminPhone(bind.edManagePhone.getText().toString());
        projectPartyData.setCreateTime(MyData.getData());
        projectPartyData.setUpdateTime(MyData.getData());
        projectPartyData.setRemark("345039");
        list.add(projectPartyData);
        if(!editTexts.isEmpty()){

            for(int i=0;i<editTexts.size();i++){
                ProjectPartyData  projectPartyData2 =new ProjectPartyData();
                projectPartyData2=projectPartyData;
                projectPartyData.setSuccessful(editTexts.get(i).getText().toString());
                i++;
                projectPartyData.setSection(editTexts.get(i).getText().toString());
                i++;
                projectPartyData.setBiddingPrice(Integer.parseInt(editTexts.get(i).getText().toString()));
                i++;
                projectPartyData.setPhone(editTexts.get(i).getText().toString());
                list.add(projectPartyData2);
            }

        }
        String code = bind.edCode.getText().toString();
        if(TextUtils.isEmpty(code)){
            PopTip.show("验证码不能为空");
            return;
        }
        ApiClient.getClient().create(ApiService.class).PostProjectPartyData(list,111,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("注册中");
                    }

                    @Override
                    public void onNext(Response s) {
                        WaitDialog.dismiss();
                        if(s.getCode()==200){
                            PopTip.show("完成注册");
                        }else{
                            PopTip.show("注册失败");
                        }

                        X.L("s="+s.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        PopTip.show("连接失败");
                        WaitDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void getcode(){
        String phone = bind.edManagePhone.getText().toString();
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

    @Override
    protected void onclick() {
        bind.tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getcode();
            }
        });
        bind.ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSubmit();
            }
        });
        bind.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewToLayout(bind.llMid2);
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addViewToLayout(LinearLayout parentLayout) {
        // 实例化新布局
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        // 可以对新布局的视图元素进行配置，例如设置文本等
        // 将新布局添加到父布局中
       EditText ed = itemView.findViewById(R.id.textInputEditTextWinningUnit);
        EditText ed2 = itemView.findViewById(R.id.textInputEditTextLot);
        EditText ed3 = itemView.findViewById(R.id.textInputEditTextWinningPrice);
        EditText ed4 = itemView.findViewById(R.id.textInputEditTextPhone);
        editTexts.add(ed);
        editTexts.add(ed2);
        editTexts.add(ed3);
        editTexts.add(ed4);
        parentLayout.addView(itemView);
    }

    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
}