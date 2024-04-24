package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityMaterialHistoryBinding;
import com.xk.porject.databinding.ItemMaterialInfoBinding;
import com.xk.porject.home.adaoter.CommonAdapter;
import com.xk.porject.home.data.MaterialInfo;

import java.util.ArrayList;
import java.util.List;

public class MaterialHistoryActivity extends BaseActivityPortrait<ActivityMaterialHistoryBinding> {

    private CommonAdapter<ItemMaterialInfoBinding, MaterialInfo> commonAdapter;
    private List<MaterialInfo> list = new ArrayList<>();

    private void addData(){
        MaterialInfo materialInfo = new MaterialInfo(
                "2024年6月27日12点28分", // 提交时间
                "2024年6月27日12点28分", // 批准时间
                "螺纹钢", // 名称
                "Φ28", // 型号
                "吨", // 规格
                1000, // 数量，注意这里我们假设"吨"只是单位，实际数量是整数
                "228别墅区基础", // 用途
                "已经采购", // 状态
                "2025年8月1日12点前" // 预计到达时间
        );
        list.add(materialInfo);
    }

    @Override
    protected void initData() {
        addData();
        commonAdapter =new CommonAdapter<ItemMaterialInfoBinding, MaterialInfo>(list) {
            @Override
            protected void show(ItemMaterialInfoBinding holder, int position, MaterialInfo materialInfo) {
                // 设置提交时间
                holder.tvsubmitTime.setText(materialInfo.getSubmitTime());

                // 设置批准时间
                holder.tvapprovaltime.setText(materialInfo.getApprovalTime());

                // 设置名称
                holder.tvName.setText(materialInfo.getName());

                // 设置型号
                holder.tvModel.setText(materialInfo.getModel());

                // 设置规格
                holder.tvspecification.setText(materialInfo.getSpecification());

                // 设置数量
                // 假设你的布局中有一个表示数量的 TextView 且其 id 为 tvQuantity
                holder.tvquantity.setText(String.valueOf(materialInfo.getQuantity()) + " 吨");

                // 设置用途
                holder.tvusage.setText(materialInfo.getUsage());

                // 设置状态
                holder.tvstatus.setText(materialInfo.getStatus());

                // 设置预计到达时间
                holder.tvestimatedarrivaltime.setText(materialInfo.getEstimatedArrivalTime());
            }
        };
        bind.rv.setAdapter(commonAdapter);
    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));

    }
}