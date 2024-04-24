package adminproject;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.adminproject.databinding.AdActivityContractorListBinding;
import com.xk.adminproject.databinding.AdItemConstratorBinding;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;

import java.util.ArrayList;
import java.util.List;


import adminproject.data.ProjectInfo;

public class ContractorListActivity extends BaseActivityPortrait<AdActivityContractorListBinding> {

    CommonAdapter<AdItemConstratorBinding, ProjectInfo> commonAdapter;

    @Override
    protected void initData() {
        List<ProjectInfo> sampleData = new ArrayList<>();
        sampleData.add(new ProjectInfo("内蒙古金兴机械 涡轮设计生产项目", "编号：", "MM025635", "注册时间：", "2015-02-08", "待开工"));
        sampleData.add(new ProjectInfo("上海轨道交通 设备维护项目", "编号：", "SH200045", "注册时间：", "2018-06-15", "待开工"));
        sampleData.add(new ProjectInfo("广东电信 5G网络扩展项目", "编号：", "GD989003", "注册时间：", "2020-11-20", "待开工"));

        commonAdapter =new CommonAdapter<AdItemConstratorBinding, ProjectInfo>(sampleData) {
            @Override
            protected void show(AdItemConstratorBinding holder, int position, ProjectInfo projectInfo) {
                holder.tvProjectTitle.setText(projectInfo.getProjectTitle());
                holder.tvNumber.setText(projectInfo.getNumber());
                holder.tvRegistrationTime.setText(projectInfo.getRegistrationTime());
                holder.tvCompanyStatus.setText(projectInfo.getCompanyStatus());
                holder.tvLook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(ContractorListActivity.this, DetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
        };
        bind.rv.setAdapter(commonAdapter);
    }

    @Override
    protected void onclick() {
            bind.back.setOnClickListener(new View.OnClickListener() {
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