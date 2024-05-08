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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseExamResponse;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.QuestionItem;
import com.xk.porject.databinding.ActivityExamBamkBinding;
import com.xk.porject.databinding.ItemExambanklBinding;
import com.xk.porject.viewmodel.ExamViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.functions.Consumer;

public class ExamBamkActivity extends BaseActivityPortrait<ActivityExamBamkBinding> {

    private ExamViewModel examViewModel;
    CommonAdapter<ItemExambanklBinding, ResponseExamResponse.Row> commonAdapter;
    private ResponseExamResponse examResponse;

    private final List<String> popupFrequencyList=Arrays.asList("每日弹出", "每周弹出", "每月弹出", "不弹出");
    private final List<String> permissionLevelList=Arrays.asList("全部", "分包商及以下", "施工队及以下");
    private final List<String> topicList=Arrays.asList("安全教育", "劳动合同");
    private final List<String> priorityList=Arrays.asList("非优先答阅", "优先答阅");

    private String date;

    @Override
    protected void initData() {
        date = getIntent().getStringExtra("date");
    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonAdapter =new CommonAdapter<ItemExambanklBinding, ResponseExamResponse.Row>(new ArrayList<>()) {
            @Override
            protected void show(ItemExambanklBinding holder, int position, ResponseExamResponse.Row questionItem) {

                holder.tvQuestionName.setText(questionItem.getExamName());
                holder.spinnerOption1.attachDataSource(new ArrayList<>(popupFrequencyList));
                holder.spinnerOption2.attachDataSource(new ArrayList<>(permissionLevelList));
                holder.spinnerOption3.attachDataSource(new ArrayList<>(topicList));
                holder.spinnerOption4.attachDataSource(new ArrayList<>(priorityList));
                holder.tvPublish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       start();
                    }
                });
                holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(c, SafetyActivity.class);
                        intent.putExtra("mode",questionItem.getId());
                        startActivity(intent);
                        finish();
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
        register();
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        examViewModel.getExamBankData().observe(this, new Observer<ResponseExamResponse>() {
            @Override
            public void onChanged(ResponseExamResponse responseExamResponse) {
                examResponse=responseExamResponse;
                commonAdapter.setData(examResponse.getRows());
            }
        });
        examViewModel.getExam();
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}