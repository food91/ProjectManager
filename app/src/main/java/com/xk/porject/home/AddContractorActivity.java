package com.xk.porject.home;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.kongzue.dialogx.dialogs.PopMenu;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.xk.base.data.AddContractData;
import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.Response;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityContractor2Binding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddContractorActivity extends BaseActivityPortrait<ActivityContractor2Binding> {


    private List<EditText> editTexts;
    private List<String> projectName;
    private List<String> cName;
    private List<String> phoneName;

    @Override
    protected void initData() {
        editTexts = new ArrayList<>();
        projectName =getIntent().getStringArrayListExtra("project");
        cName =getIntent().getStringArrayListExtra("cname");
        phoneName =getIntent().getStringArrayListExtra("cphone");
        assert projectName != null;
        bind.spProjectname.attachDataSource(projectName);
    }
    private void registerSubmit(){
        List<AddContractData> list = new ArrayList<>();
        AddContractData projectPartyData =new AddContractData();
        projectPartyData.setCreateTime(MyData.getData());
        projectPartyData.setUpdateTime(MyData.getData());
        String name = bind.spProjectname.getText().toString();
        String phone = bind.edContractorPhone.getText().toString();
        String addc = bind.edAddcName.getText().toString();
        String price = bind.edPriceName.getText().toString();
        String pricenum = bind.edPriceSum.getText().toString();
        String company = bind.edSuperviesionCompany.getText().toString();
        if(TextUtils.isEmpty(name)||
                TextUtils.isEmpty(addc)||
                TextUtils.isEmpty(price)||
                TextUtils.isEmpty(pricenum)||
                TextUtils.isEmpty(company)
        ){
            PopTip.show("输入不能有空项");
            return;
        }
        projectPartyData.setProjectName(name);
        projectPartyData.setContractName(addc);
        projectPartyData.setContractNumber(phone);
        projectPartyData.setSection(price);
        projectPartyData.setLumpSum(Integer.parseInt(pricenum));
        projectPartyData.setSuperviesionCompany(company);
        list.add(projectPartyData);
        if(!editTexts.isEmpty()){
            for(int i=0;i<editTexts.size();i++){
                AddContractData  projectPartyData2 =new AddContractData();
                projectPartyData2=projectPartyData;
                projectPartyData.setProjectName(editTexts.get(i).getText().toString());
                i++;
                projectPartyData.setContractName(editTexts.get(i).getText().toString());
                i++;
                projectPartyData.setSection(editTexts.get(i).getText().toString());
                i++;
                projectPartyData.setLumpSum(Integer.parseInt(editTexts.get(i).getText().toString()));
                i++;
                projectPartyData.setContractName(editTexts.get(i).getText().toString());
                list.add(projectPartyData2);
            }

        }
        ApiClient.getClient().create(ApiService.class).AddContract(projectPartyData)
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

    @Override
    protected void onclick() {
        bind.ivFindCphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] c_phone = phoneName.toArray(new String[0]);
                PopMenu.show(bind.edContractorPhone,c_phone)
                        .setOverlayBaseView(true)
                        .setAlignGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<PopMenu>() {
                            @Override
                            public boolean onClick(PopMenu popMenu, CharSequence charSequence, int i) {
                                bind.edContractorPhone.setText(charSequence);
                                return false;
                            }
                        });
            }
        });
        bind.ivFindContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] c_name = cName.toArray(new String[0]);
                PopMenu.show(bind.edAddcName,c_name)
                        .setOverlayBaseView(true)
                        .setAlignGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<PopMenu>() {
                            @Override
                            public boolean onClick(PopMenu popMenu, CharSequence charSequence, int i) {
                                bind.edAddcName.setText(charSequence);
                                return false;
                            }
                        });
            }
        });
        bind.tvAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSubmit();
            }
        });

        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewToLayout(bind.llMain);
            }
        });
    }

    private void addViewToLayout(LinearLayout parentLayout) {
        // 实例化新布局
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_addcon, null);
        // 可以对新布局的视图元素进行配置，例如设置文本等
        // 将新布局添加到父布局中
        EditText ed = itemView.findViewById(R.id.ed_projectname);
        EditText ed2 = itemView.findViewById(R.id.ed_addc);
        EditText ed3 = itemView.findViewById(R.id.ed_contractor_name);
        EditText ed4 = itemView.findViewById(R.id.ed_price_name);
        EditText ed5 = itemView.findViewById(R.id.ed_price_sum);
        EditText ed6 = itemView.findViewById(R.id.ed_superviesion_company);
        editTexts.add(ed);
        editTexts.add(ed2);
        editTexts.add(ed3);
        editTexts.add(ed4);
        editTexts.add(ed5);
        editTexts.add(ed6);
        parentLayout.addView(itemView);
    }

    @Override
    protected void initPortraitView() {

    }
}