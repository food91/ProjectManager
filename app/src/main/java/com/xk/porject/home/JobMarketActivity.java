package com.xk.porject.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.bumptech.glide.Glide;
import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.ResponseFindWork;
import com.xk.base.data.ResponseProjectPostInfo;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.ui.spinner.NiceSpinner;
import com.xk.base.ui.spinner.OnSpinnerItemSelectedListener;
import com.xk.porject.databinding.ActivityJobMarketBinding;
import com.xk.porject.databinding.ItemFindworkerBinding;
import com.xk.porject.databinding.ItemJbomarketBinding;
import com.xk.porject.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class JobMarketActivity extends BaseActivityPortrait<ActivityJobMarketBinding> {

    CommonAdapter<ItemJbomarketBinding,ResponseFindWork.Row> commonAdapterJob;
    CommonAdapter<ItemFindworkerBinding,ResponseProjectPostInfo.Row> commonAdapterProject;
    String[] tile={"人才市场","招聘"};
    String[] tile2={"增加招聘方","增加求职方"};

    List<ResponseProjectPostInfo.Row> dataProject;
    List<ResponseFindWork.Row> datafindwork;
    @Override
    protected void initData() {
        initadapter();
        setadapter();
        if(bind.spTitle.getSelectedIndex()==0){
            getJobInfor();
        }else{
            getProjectInfo();
        }

    }

    private void initadapter(){
        commonAdapterProject = new CommonAdapter<ItemFindworkerBinding, ResponseProjectPostInfo.Row>(new ArrayList<>()) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void show(ItemFindworkerBinding holder, int position, ResponseProjectPostInfo.Row row) {
               Glide.with(c).load(row.getImages()).into(holder.ivHead);
                holder.tvNameLab.setText("项目名称:"+row.getProjectName());
                holder.tvAddressLab.setText("项目地址"+row.getProjectAddress());
                holder.tvJob.setText("招聘:"+row.getDemand());
                holder.tvRequest.setText("要求:"+row.getTechnologyDemand());
                holder.tvState.setText(row.getStatus()+"天后完工");
                if(row.getCurrentState()==0){
                    holder.tvStateWork.setText("当前状态：在建");
                }else{
                    holder.tvStateWork.setText("当前状态：完工");
                }
                holder.ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPhone(row.getPhone());
                    }
                });
            }
        };
        commonAdapterJob = new CommonAdapter<ItemJbomarketBinding, ResponseFindWork.Row>(new ArrayList<>()) {
            @Override
            protected void show(ItemJbomarketBinding holder, int position, ResponseFindWork.Row row) {
                Glide.with(c).load(row.getImage()).into(holder.ivHead);
                holder.tvName.setText(row.getName());
                holder.tvNative.setText("籍贯:"+row.getNativePlace());
                holder.tvShow.setText("特长:"+row.getStrongPoint());
                holder.tvStateWork.setText(Utils.getWorkState_str(row.getWorkerStatus()));
                holder.tvState.setText(row.getWorkerStatus()+"天后空闲");
                holder.ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPhone(row.getPhone());
                    }
                });
            }
        };
    }



    private void getJobInfor(){
        performApiCall(ApiClient.getClient().create(ApiService.class).getJobList(), new Consumer<ResponseFindWork>() {
            @Override
            public void accept(ResponseFindWork response) throws Exception {
                if(response.getCode()==200){
                    datafindwork = response.getRows();
                    bind.rv.setAdapter(commonAdapterJob);
                    commonAdapterJob.setData(datafindwork);
                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                PopTip.show("连接失败");
            }
        });
    }

    private void getProjectInfo(){
        performApiCall(ApiClient.getClient().create(ApiService.class).getProectList(), new Consumer<ResponseProjectPostInfo>() {
            @Override
            public void accept(ResponseProjectPostInfo response) throws Exception {
                if(response.getCode()==200){
                        dataProject = response.getRows();
                        bind.rv.setAdapter(commonAdapterProject);
                        commonAdapterProject.setData(dataProject);
                }else{
                    PopTip.show(response.getMsg());
                }
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

        bind.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.show(tile2).setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                    @Override
                    public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                        if(i==0){
                            Intent intent =new Intent(c, AddInformationActivity.class);
                            startActivity(intent);

                        }else if(i==1){
                            Intent intent =new Intent(c, JobInformationActivity.class);
                            startActivity(intent);
                        }
                        return false;
                    }
                }).show();
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void startPhone(String num){
        String phoneNumber =num; // 示例电话号码
// 创建一个Intent对象，设置动作为ACTION_DIAL
        X.L("startPhone");
        Intent intent = new Intent(Intent.ACTION_DIAL);
// 设置电话号码
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
// 启动Intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void setadapter(){

        if(bind.spTitle.getSelectedIndex()==0){
            bind.rv.setAdapter(commonAdapterJob);
        }else{
            bind.rv.setAdapter(commonAdapterProject);
        }
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        List<String> dataset = new LinkedList<>(Arrays.asList(tile));
        bind.spTitle.attachDataSource(dataset);
        bind.spTitle.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                        if(position==0){
                            getJobInfor();
                        }else{
                            getProjectInfo();
                        }
            }
        });
    }
}