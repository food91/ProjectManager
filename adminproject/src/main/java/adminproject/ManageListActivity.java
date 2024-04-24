
package adminproject;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.adminproject.databinding.AdActivityManageListBinding;
import com.xk.adminproject.databinding.AdItemManageBinding;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.ui.BaseActivityPortrait;

import java.util.ArrayList;
import java.util.List;


import adminproject.data.ManagerInfo;

public class ManageListActivity extends BaseActivityPortrait<AdActivityManageListBinding> {

    private CommonAdapter<AdItemManageBinding, ManagerInfo> commonAdapter;
    List<ManagerInfo> managerList;
    @Override
    protected void initData() {
        managerList =new ArrayList<>();
        managerList.add(new ManagerInfo(
                "内蒙古新风风电项目",
                "李小沫",
                "15656356326"
        ));

        managerList.add(new ManagerInfo(
                "京津冀协同发展项目",
                "张婉清",
                "13245678901"
        ));

        managerList.add(new ManagerInfo(
                "广东珠海横琴新区建设项目",
                "王大明",
                "13912345678"
        ));
    }

    @Override
    protected void onclick() {
        commonAdapter = new CommonAdapter<AdItemManageBinding, ManagerInfo>(managerList) {
            @Override
            protected void show(AdItemManageBinding holder, int position, ManagerInfo managerInfo) {
                holder.projectLabelTextView.setText(managerInfo.getProjectName());
                holder.managerContactTextView.setText(managerInfo.getManagerName());
                holder.projectManagerNameTextView.setText(managerInfo.getContactNumber());

            }
        };
        bind.rv.setAdapter(commonAdapter);
        bind.tvAddmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ManageListActivity.this, AddManageListActivity.class);
                startActivity(intent);
            }
        });
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