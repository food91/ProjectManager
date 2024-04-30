package com.xk.porject.home;


import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.WorkDayData;
import com.xk.porject.databinding.ActivityWorkerBinding;
import com.xk.porject.databinding.ItemworkBinding;
import com.xk.porject.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class WorkerActivity extends BaseActivityPortrait<ActivityWorkerBinding> {

    private List<WorkDayData> data;
    String mode="";
    private CommonAdapter<ItemworkBinding,WorkDayData> commonAdapter;
    @Override
    protected void initData() {
        mode = getIntent().getStringExtra("count");
        data=new ArrayList<>();
        int sum = Utils.getAllDayMon();
        for(int i=0;i<=sum+7;i++){
            int d = (int) (Math.random() * 255);
            if(i<=sum){
                data.add(new WorkDayData(d,Utils.getWeekDay(i),i));
            }else{
                data.add(new WorkDayData(-1,-1,-1));
            }

        }
    }

    @Override
    protected void onclick() {
        if(mode!=null&&mode.equals("count")){
            bind.titleText.setText("我的计件");
            bind.tvSum.setText("本身计件共计:5563");
        }
        if(mode!=null&&mode.equals("Salary")){
            bind.titleText.setText("我的工资");
            bind.tvSum.setText("本身工资共计:5563");
        }
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonAdapter =new CommonAdapter<ItemworkBinding, WorkDayData>(data) {

            @Override
            public int getItemCount() {
                return data.size()/7+1;
            }

            @Override
            protected void show(ItemworkBinding holder, int position, WorkDayData workDayData) {
                if(position==0){
                    holder.llData.setVisibility(View.GONE);
                    holder.tv1.setTextColor(Color.BLACK);
                    holder.tv1.setBackgroundColor(Color.WHITE);
                    holder.tv2.setTextColor(Color.BLACK);
                    holder.tv2.setBackgroundColor(Color.WHITE);
                    holder.tv3.setTextColor(Color.BLACK);
                    holder.tv3.setBackgroundColor(Color.WHITE);
                    holder.tv4.setTextColor(Color.BLACK);
                    holder.tv4.setBackgroundColor(Color.WHITE);
                    holder.tv5.setTextColor(Color.BLACK);
                    holder.tv5.setBackgroundColor(Color.WHITE);
                    holder.tv6.setTextColor(Color.BLACK);
                    holder.tv6.setBackgroundColor(Color.WHITE);
                    holder.tv7.setTextColor(Color.BLACK);
                    holder.tv7.setBackgroundColor(Color.WHITE);
                    return;
                }else{
                    holder.llData.setVisibility(View.VISIBLE);
                }
                int num =(position-1)*7+1;
                if(num>data.size()){
                    holder.llData.setVisibility(View.GONE);
                    return;
                }
                int start =  data.get(num).getWeekday();
                if(start==-1){
                    holder.llData.setVisibility(View.GONE);
                    return;
                }
                 if(start==1){
                     holder.tv1data.setText(data.get(num).getDataString());
                     holder.tv1.setText(data.get(num++).getDayString()+"");
                     holder.tv2data.setText(data.get(num).getDataString());
                     holder.tv2.setText(data.get(num++).getDayString()+"");
                     holder.tv3data.setText(data.get(num).getDataString());
                     holder.tv3.setText(data.get(num++).getDayString()+"");
                     holder.tv4data.setText(data.get(num).getDataString());
                     holder.tv4.setText(data.get(num++).getDayString()+"");
                     holder.tv5data.setText(data.get(num).getDataString());
                     holder.tv5.setText(data.get(num++).getDayString()+"");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }else if(start==2) {
                     holder.tv1.setText("");
                     holder.tv2data.setText(data.get(num).getDataString());
                     holder.tv2.setText(data.get(num++).getDayString()+"");
                     holder.tv3data.setText(data.get(num).getDataString());
                     holder.tv3.setText(data.get(num++).getDayString()+"");
                     holder.tv4data.setText(data.get(num).getDataString());
                     holder.tv4.setText(data.get(num++).getDayString()+"");
                     holder.tv5data.setText(data.get(num).getDataString());
                     holder.tv5.setText(data.get(num++).getDayString()+"");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 } else if(start==3){
                     holder.tv1.setText("");
                     holder.tv2.setText("");
                     holder.tv3.setText(data.get(num++).getDayString()+"");
                     holder.tv3data.setText(data.get(num).getDataString());
                     holder.tv4.setText(data.get(num++).getDayString()+"");
                     holder.tv4data.setText(data.get(num).getDataString());
                     holder.tv5.setText(data.get(num++).getDayString()+"");
                     holder.tv5data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }else if(start==4){
                     holder.tv1.setText("");
                     holder.tv2.setText("");
                     holder.tv3.setText("");
                     holder.tv4data.setText(data.get(num).getDataString());
                     holder.tv4.setText(data.get(num++).getDayString()+"");
                     holder.tv5data.setText(data.get(num).getDataString());
                     holder.tv5.setText(data.get(num++).getDayString()+"");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }else if(start==5){
                     holder.tv1.setText("");
                     holder.tv2.setText("");
                     holder.tv3.setText("");
                     holder.tv4.setText("");
                     holder.tv5data.setText(data.get(num).getDataString());
                     holder.tv5.setText(data.get(num++).getDayString()+"");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }else if(start==6){
                     holder.tv1.setText("");
                     holder.tv2.setText("");
                     holder.tv3.setText("");
                     holder.tv4.setText("");
                     holder.tv5.setText("");
                     holder.tv6data.setText(data.get(num).getDataString());
                     holder.tv6.setText(data.get(num++).getDayString()+"");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }else if(start==7){
                     holder.tv1.setText("");
                     holder.tv2.setText("");
                     holder.tv3.setText("");
                     holder.tv4.setText("");
                     holder.tv5.setText("");
                     holder.tv6.setText("");
                     holder.tv7data.setText(data.get(num).getDataString());
                     holder.tv7.setText(data.get(num++).getDayString()+"");
                 }

            }
        };
    bind.rv.setAdapter(commonAdapter);
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}