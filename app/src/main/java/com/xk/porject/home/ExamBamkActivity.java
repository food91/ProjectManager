package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.QuestionItem;
import com.xk.porject.databinding.ActivityExamBamkBinding;
import com.xk.porject.databinding.ItemExambanklBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExamBamkActivity extends BaseActivityPortrait<ActivityExamBamkBinding> {

    CommonAdapter<ItemExambanklBinding, QuestionItem> commonAdapter;
    List<QuestionItem> list;
    @Override
    protected void initData() {
        list = new ArrayList<>();
        // 添加一些示例数据
        list.add(new QuestionItem("测试题名称 1", "编辑", "已发布", "无草稿", "2022-01-01"));
        list.add(new QuestionItem("测试题名称 2", "未编辑", "未发布", "有草稿", "2022-01-02"));
        list.add(new QuestionItem("测试题名称 3", "编辑中", "已发布", "有草稿", "2022-01-03"));
        list.add(new QuestionItem("测试题名称 4", "未编辑", "未发布", "无草稿", "2022-01-04"));
        list.add(new QuestionItem("测试题名称 5", "编辑", "已发布", "有草稿", "2022-01-05"));
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
            }
        };
        bind.rv.setAdapter(commonAdapter);
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
    }
}