package com.xk.porject.home;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.QuestionItem;
import com.xk.porject.databinding.ActivityExamBamkBinding;
import com.xk.porject.databinding.ItemExambanklBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.functions.Consumer;

public class ExamBamkActivity extends BaseActivityPortrait<ActivityExamBamkBinding> {

    CommonAdapter<ItemExambanklBinding, QuestionItem> commonAdapter;
    List<QuestionItem> list;
    private final List<String> popupFrequencyList=Arrays.asList("每日弹出", "每周弹出", "每月弹出", "不弹出");
    private final List<String> permissionLevelList=Arrays.asList("全部", "分包商及以下", "施工队及以下");
    private final List<String> topicList=Arrays.asList("安全教育", "劳动合同");
    private final List<String> priorityList=Arrays.asList("非优先答阅", "优先答阅");

    private String date;

    @Override
    protected void initData() {
        date = getIntent().getStringExtra("date");
        list = new ArrayList<>();
        // 添加一些示例数据
        list.add(new QuestionItem("测试题名称 1", "编辑", "已发布", "无草稿", "2022-01-01"));
        list.add(new QuestionItem("测试题名称 2", "未编辑", "未发布", "有草稿", "2022-01-02"));
        list.add(new QuestionItem("测试题名称 3", "编辑中", "已发布", "有草稿", "2022-01-03"));
        list.add(new QuestionItem("测试题名称 4", "未编辑", "未发布", "无草稿", "2022-01-04"));
        list.add(new QuestionItem("测试题名称 5", "编辑", "已发布", "有草稿", "2022-01-05"));
        performApiCall(ApiClient.getClient().create(ApiService.class).gettestlist(), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){

                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonAdapter =new CommonAdapter<ItemExambanklBinding, QuestionItem>(list) {
            @Override
            protected void show(ItemExambanklBinding holder, int position, QuestionItem questionItem) {
                holder.tvDate.setText(questionItem.getDate());
                holder.tvQuestionName.setText(questionItem.getQuestionName());
                holder.spinnerOption1.attachDataSource(new ArrayList<>(popupFrequencyList));
                holder.spinnerOption2.attachDataSource(new ArrayList<>(permissionLevelList));
                holder.spinnerOption3.attachDataSource(new ArrayList<>(topicList));
                holder.spinnerOption4.attachDataSource(new ArrayList<>(priorityList));
                holder.tvPublish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(date)){
                            PopTip.show("请选择试题截止时候后再发布");
                            return;
                        }
                       start();
                    }
                });
                holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        };
        bind.rv.setAdapter(commonAdapter);
    }

    private void start(){
        Intent intent=new Intent(c, ChooseWorkerActivity.class);
        startActivityForResult.launch(intent);
    }
    private ActivityResultLauncher<Intent> startActivityForResult;
    private void register(){
        startActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                        }
                    }
                });
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}