package com.xk.porject.home;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.widget.TextView;

import com.kongzue.dialogx.datepicker.CalendarDialog;
import com.kongzue.dialogx.datepicker.interfaces.OnDateSelected;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.PostScheduleData;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.data.ResponseProjectPostInfo;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityCreateSinglePlanBinding;
import com.xk.porject.utils.Utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import io.reactivex.functions.Consumer;

public class CreateSinglePlanActivity extends BaseActivityPortrait<ActivityCreateSinglePlanBinding> {

    private List<ResponseFindlist.Datum> projectdata;

    @Override
    protected void initData() {
        performApiCall(ApiClient.getClient().create(ApiService.class).findlistproect(),
                new Consumer<ResponseFindlist>() {
            @Override
            public void accept(ResponseFindlist responseProjectPostInfo) throws Exception {
                if(responseProjectPostInfo.getCode()==200){
                   projectdata = responseProjectPostInfo.getData();
                    List<String> projectNames = new LinkedList<>(); // 创建一个新的列表来存储项目名称
                    for (ResponseFindlist.Datum datum : projectdata) { // 遍历项目数据列表
                        projectNames.add(datum.getProjectName()); // 将每个项目的名称添加到新列表中
                    }
                    bind.spName.attachDataSource(projectNames);
                }else{
                    PopTip.show(responseProjectPostInfo.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                PopTip.show(throwable.getMessage());
            }
        });
    }

    void showDataPicket(TextView tv){
        CalendarDialog.build()
                .show(new OnDateSelected() {
                    @Override
                    public void onSelect(String text, int year, int month, int day) {
                        tv.setText(text);
                    }
                });
    }

    private void addPlan(){
        String name = bind.spName.getSelectedItem().toString();
        String ename = bind.edName.getText().toString();
        String eday = bind.projectPlaneTime.getText().toString();
        String tvstart = bind.tvShowDateStart.getText().toString();
        String tvend = bind.tvShowDateEnd.getText().toString();
        boolean manage =bind.swManage.isChecked();
        if(!Utils.areAllStringsValid(name,ename,eday,tvstart,tvend)){
            PopTip.show("输入不能有空项存在");
            return;
        }

        try {

            String   formattedStartDate=   Utils.formatDateWithDefaultTime(tvstart);
            String  formattedEndDate=  Utils.formatDateWithDefaultTime(tvend);
            PostScheduleData scheduleData =new PostScheduleData();
            scheduleData.setPName(name);
            scheduleData.setEName(ename);
            scheduleData.setPlannedTime(eday);
            scheduleData.setCreateTime(formattedStartDate);
            scheduleData.setEndTime(formattedEndDate);
            if(manage){
                scheduleData.setScheduleManagement(0);
            }else{
                scheduleData.setScheduleManagement(1);
            }
            performApiCall(ApiClient.getClient().create(ApiService.class).addScheduleData(scheduleData),
                    new Consumer<Response>() {
                        @Override
                        public void accept(Response response) throws Exception {
                            if(response.getCode()==200){
                                PopTip.show(response.getMsg());
                            }else{
                                PopTip.show(response.getMsg());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            PopTip.show("连接失败");
                        }
                    });
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    protected void onclick() {
        bind.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlan();
            }
        });
        bind.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.flT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPicket(bind.tvShowDateStart);
            }
        });
        bind.flT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPicket(bind.tvShowDateEnd);
            }
        });

        bind.backIcon.setOnClickListener(new View.OnClickListener() {
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