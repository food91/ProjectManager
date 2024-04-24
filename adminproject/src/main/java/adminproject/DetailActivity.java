package adminproject;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.adminproject.databinding.AdActivityDetailBinding;
import com.xk.adminproject.databinding.AdItemDetailBinding;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;

import java.util.ArrayList;
import java.util.List;


import adminproject.data.ProjectDetails;

public class DetailActivity extends BaseActivityPortrait<AdActivityDetailBinding> {

    CommonAdapter<AdItemDetailBinding, ProjectDetails> commonAdapter;
    List<ProjectDetails> projectList;
    @Override
    protected void initData() {
        projectList = new ArrayList<>();

        // 创建并添加项目详情数据到列表
        projectList.add(new ProjectDetails(
                "内蒙古金兴涡轮设计生产项目",
                "81,000,000.00 元",
                "45,260,000.00 元",
                "56%",
                108,
                "未更改",
                "每月一次"
        ));

        projectList.add(new ProjectDetails(
                "内蒙古金兴涡轮二期扩展项目",
                "120,000,000.00 元",
                "70,000,000.00 元",
                "58%",
                150,
                "已更改",
                "每周一次"
        ));

        projectList.add(new ProjectDetails(
                "新能源发展项目",
                "200,000,000.00 元",
                "160,000,000.00 元",
                "80%",
                200,
                "正在评估",
                "每季度一次"
        ));
    }

    @Override
    protected void onclick() {
        commonAdapter =new CommonAdapter<AdItemDetailBinding, ProjectDetails>(projectList) {
            @Override
            protected void show(AdItemDetailBinding holder, int position, ProjectDetails projectDetails) {
// 设置初始状态为隐藏
                holder.mainInfoLinearLayout.setVisibility(View.GONE);

                // 设置selectTextView的点击监听器
                holder.selectTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 切换mainInfoLinearLayout的可见性
                        if (holder.mainInfoLinearLayout.getVisibility() == View.VISIBLE) {
                            holder.mainInfoLinearLayout.setVisibility(View.GONE);
                        } else {
                            holder.mainInfoLinearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                });

                // 其他数据绑定操作
                holder.projectTitleTextView.setText(projectDetails.getProjectTitle());
                holder.fundingAmountTextView.setText(projectDetails.getFundingAmount());
                holder.paymentAmountTextView.setText(projectDetails.getPaymentAmount());
                holder.paymentRatioTextView.setText(projectDetails.getPaymentRatio());
                holder.totalStaffTextView.setText(String.valueOf(projectDetails.getTotalStaff()));
            }
        };
        bind.rv.setAdapter(commonAdapter);
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