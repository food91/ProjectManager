package com.xk.porject.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.adapter.ExpandableListAdapter;

import com.xk.porject.contractor.WorkerInfoActivity;
import com.xk.porject.data.ExpandableListItem;
import com.xk.porject.databinding.ActivityWorkManageBinding;

import java.util.ArrayList;
import java.util.List;

public class WorkManageActivity extends BaseActivityPortrait<ActivityWorkManageBinding> {

    List<ExpandableListItem> itemList = generateData();
    @Override
    protected void initData() {

    }
    public static List<ExpandableListItem> generateData() {
        // 创建根级列表项
        ExpandableListItem rootItem = new ExpandableListItem("正蓝旗娱乐城装饰工程", 0);
        // 创建第一层子项
        ExpandableListItem firstLevelItem1 = new ExpandableListItem("工程部门", 3);
        ExpandableListItem firstLevelItem2 = new ExpandableListItem("施工部门", 4);
        ExpandableListItem firstLevelItem3 = new ExpandableListItem("土建部门", 5);

        // 创建第二层子项，对应于“团队高级成员”
        firstLevelItem1.addChild(new ExpandableListItem("王经理(56)", 0));
        firstLevelItem1.addChild(new ExpandableListItem("李总监(55)", 0));
        firstLevelItem1.addChild(new ExpandableListItem("张部长(31)", 0));

        // 创建第二层子项，对应于“团队中级成员”
        firstLevelItem2.addChild(new ExpandableListItem("刘公公(18)", 0));
        firstLevelItem2.addChild(new ExpandableListItem("谢大嘴(32)", 0));
        firstLevelItem2.addChild(new ExpandableListItem("蔡律师(12)", 0));
        firstLevelItem2.addChild(new ExpandableListItem("周流官(7)", 0));

        // 创建第二层子项，对应于“团队初级成员”
        firstLevelItem3.addChild(new ExpandableListItem("吴兵长(19)", 0));
        firstLevelItem3.addChild(new ExpandableListItem("陈木匠(2)", 0));
        firstLevelItem3.addChild(new ExpandableListItem("郑水手(5)", 0));
        firstLevelItem3.addChild(new ExpandableListItem("林石匠(3)", 0));
        firstLevelItem3.addChild(new ExpandableListItem("朱铁匠(1)", 0));

        // 将所有一级子项添加到根项
        rootItem.addChild(firstLevelItem1);
        rootItem.addChild(firstLevelItem2);
        rootItem.addChild(firstLevelItem3);

        // 根项列表，作为顶级容器
        List<ExpandableListItem> itemList = new ArrayList<>();
        itemList.add(rootItem);

        return itemList;
    }

    @Override
    protected void onclick() {
        ExpandableListAdapter adapter = new ExpandableListAdapter(itemList);
        bind.rv.setAdapter(adapter);
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WorkManageActivity.this, WorkerInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {

    }
}