package com.xk.porject.projectmain;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.GroupInfo;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.adapter.ExpandableListAdapter;
import com.xk.porject.databinding.ActivityProjectmanageBinding;
import com.xk.porject.databinding.ActivityWorkManageBinding;
import com.xk.porject.home.WorkerInfoActivity;
import com.xk.porject.viewmodel.WorkManageViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ProjectWorkManageActivity extends BaseActivityPortrait<ActivityProjectmanageBinding> {


     GroupInfo.Data data = new GroupInfo.Data();
    List<GroupInfo.Group> path;
    GroupInfo.Group postionDatum=new GroupInfo.Group();
    GroupInfo.Data deletelist;

    private int mode;

    private WorkManageViewModel viewModel;

    private int id;
    @Override
    protected void initPortraitView() {
        mode=getIntent().getIntExtra("mode",0);
        id = getIntent().getIntExtra("id",id);
        deletelist = new GroupInfo.Data();
        deletelist.init();
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        m_setAdapter();
        viewModel  = new ViewModelProvider(this).get(WorkManageViewModel.class);
        QueryGroupWork(id,"0");
        String name = getIntent().getStringExtra("projectname");
        bind.tvPath.setText(name);
    }
    @Override
    protected void initData() {

        path=new ArrayList<>();
    }

    private void m_setAdapter(){
        adapter = new ExpandableListAdapter(data,new ExpandableListAdapter.OnItemBindListener() {
            @Override
            public void showGroup(@NonNull ExpandableListAdapter.ViewHolder holder, GroupInfo.Group item, int position) {
                holder.title.setText(item.getGroupName());
                holder.checkBox.setChecked(false);
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            deletelist.getGroup().add(item);
                        }else{
                            deletelist.getGroup().remove(item);
                        }
                    }
                });
            }

            @Override
            public void showWork(@NonNull ExpandableListAdapter.ViewHolder holder, GroupInfo.Worker item, int position) {

            }

        });
        bind.rv.setAdapter(adapter);
    }
    private void QueryGroupWork(int id,String item){

    }


    private void QueryGroup(int id,GroupInfo.Group item){
        performApiCall(ApiClient.getClient().create(ApiService.class).getgroup(item.getid()+"",id),
                new Consumer<GroupInfo>() {
                    @Override
                    public void accept(GroupInfo groupInfo) throws Exception {
                        if (groupInfo.getCode() == 200) {

                            if (postionDatum != null&&!path.isEmpty()&&
                            postionDatum.getid()==item.getid()) {

                            }else{
                                postionDatum = item;
                                path.add(postionDatum);
                                setPath();
                            }
                            deletelist.getGroup().clear();
                            data = groupInfo.getData();
                            adapter.setData(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        WaitDialog.dismiss();
                    }
                });
    }


    private void setPath(){
        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<path.size();i++){
            stringBuffer.append("/");
            stringBuffer.append(path.get(i).getGroupName());
        }
        if(TextUtils.isEmpty(stringBuffer.toString())){
            bind.tvPath.setText("/");

        }else{
            bind.tvPath.setText(stringBuffer.toString());
        }

    }
    ExpandableListAdapter adapter;
    @Override
    protected void onclick() {
        bind.tvAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c,SuperWorkerInfoActivity.class);
                startActivity(intent);
            }
        });
        bind.tvDeleteProect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bind.tvSetManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bind.tvSetManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}