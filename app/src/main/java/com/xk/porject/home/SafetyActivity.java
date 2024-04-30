package com.xk.porject.home;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivitySafetyBinding;
import com.xk.porject.databinding.ItemAddconBinding;
import com.xk.porject.databinding.ItemSafeTestBinding;

import java.util.ArrayList;
import java.util.List;

public class SafetyActivity extends BaseActivityPortrait<ActivitySafetyBinding> {

    private List<testData> list;
    private List<testData> list2;
    private CommonAdapter<ItemSafeTestBinding,testData> commonAdapter;
    private CommonAdapter<ItemSafeTestBinding,testData> commonAdapter2;

    @Override
    protected void initPortraitView() {
        bind.rv1.setLayoutManager(new LinearLayoutManager(this));
        bind.rv2.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new testData());
    }

    @Override
    protected void onclick() {
        commonAdapter = new CommonAdapter<ItemSafeTestBinding, testData>(list) {
            @Override
            protected void show(ItemSafeTestBinding holder, int position, testData t) {
                if(position>=1){
                    holder.ivDelete.setVisibility(View.VISIBLE);
                    holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.remove(t);
                            commonAdapter.notifyItemRemoved(position);
                        }
                    });
                }
                holder.cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            t.ischeck = isChecked;
                    }
                });
                holder.edInput1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        t.tile=s.toString();
                    }
                });
            }
        };
        commonAdapter2 = new CommonAdapter<ItemSafeTestBinding, testData>(list2) {
            @Override
            protected void show(ItemSafeTestBinding holder, int position, testData t) {
                if(position>=1){
                    holder.ivDelete.setVisibility(View.VISIBLE);
                    holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list2.remove(t);
                            commonAdapter.notifyItemRemoved(position);
                        }
                    });
                }
                holder.cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        t.ischeck = isChecked;
                    }
                });
                holder.edInput1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        t.tile=s.toString();
                    }
                });
            }
        };
        bind.rv2.setAdapter(commonAdapter2);
        bind.rv1.setAdapter(commonAdapter);
        bind.addRv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()<4){
                    list.add(new testData());
                }
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SafetyActivity.this, ExamBamkActivity.class);
                startActivity(intent);
            }
        });
    }

    class testData{
        public String tile;
        public boolean ischeck;
    }
}