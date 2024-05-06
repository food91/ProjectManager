package com.xk.porject.projectmain;

import android.adservices.topics.Topic;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.AddWorker;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseWorker;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.adapter.ExpandableListAdapter;
import com.xk.porject.databinding.ActivityProjectmanageBinding;
import com.xk.porject.databinding.ActivityWorkManageBinding;
import com.xk.porject.databinding.ItemManageWorkerBinding;
import com.xk.porject.databinding.ItemWorkmanageBinding;
import com.xk.porject.home.WorkerInfoActivity;
import com.xk.porject.viewmodel.WorkManageViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ProjectWorkManageActivity extends BaseActivityPortrait<ActivityProjectmanageBinding> {
     List<ResponseWorker.Data.Project>  data = new ArrayList<>();
    CommonAdapter<ItemManageWorkerBinding,ResponseWorker.Data.Project> adapter  ;
    List<GroupInfo.Group> path;
    GroupInfo.Group postionDatum=new GroupInfo.Group();
    List<ResponseWorker.Data.Project>  deletelist;
    private int mode;
    private WorkManageViewModel viewModel;

    private int id;
    @Override
    protected void initPortraitView() {
        mode=getIntent().getIntExtra("mode",0);
        id = getIntent().getIntExtra("id",id);
        deletelist = new ArrayList<>();
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
        m_setAdapter();
        viewModel  = new ViewModelProvider(this).get(WorkManageViewModel.class);
        QueryGroupWork(id);
        String name = getIntent().getStringExtra("projectname");
        bind.tvPath.setText(name);
    }
    @Override
    protected void initData() {
        path=new ArrayList<>();
    }

    private void m_setAdapter(){
        adapter = new CommonAdapter<ItemManageWorkerBinding, ResponseWorker.Data.Project>(new ArrayList<>()) {
            @Override
            protected void show(ItemManageWorkerBinding holder, int position, ResponseWorker.Data.Project project) {
                    holder.tvName.setText(project.getName());
                    if(project.getRank()==null){

                    }else{
                        if(project.getRank()==0){
                            holder.ivRank.setVisibility(View.GONE);
                        }else{
                            holder.ivRank.setVisibility(View.VISIBLE);
                        }

                    }
                    holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                deletelist.add(project);
                            }else {
                                deletelist.remove(project);
                            }
                        }
                    });
            }
        };
        bind.rv.setAdapter(adapter);
    }
    private void QueryGroupWork(int id){
        performApiCall(ApiClient.getClient().create(ApiService.class).getProjectWork(id),
                new Consumer<ResponseWorker>() {
            @Override
            public void accept(ResponseWorker w) throws Exception {
                if(w.getCode()==200){
                    data = w.getData().getProject();
                    adapter.setData(data);
                }else{
                    PopTip.show(w.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }

    private void delete(){
        if(deletelist.isEmpty()){
            PopTip.show("请选择删除人员");
            return;
        }
        StringBuffer stringBuffer =new StringBuffer();
        stringBuffer.append(deletelist.get(0).getId());
        for(int i=1;i<deletelist.size();i++){
            stringBuffer.append(",");
            stringBuffer.append(deletelist.get(i).getId());
        }
        performApiCall(ApiClient.getClient().create(ApiService.class).
                deleteWorker(stringBuffer.toString()), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    PopTip.show(response.getMsg());
                    for(int i=0;i<data.size();i++){
                        for(int j=0;j<deletelist.size();j++){
                            if(data.get(i).getId()==deletelist.get(j).getId()){
                                data.remove(i);
                            }
                        }
                    }
                    adapter.setData(data);
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

    private void setManager(){
        if(deletelist.isEmpty()){
            PopTip.show("请选择删除人员");
            return;
        }
        if(deletelist.size()>1){
            PopTip.show("组长不能批量设置");
            return;
        }
        performApiCall(ApiClient.getClient().create(ApiService.class).setGroupManage(deletelist.get(0).getId()),
                new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        PopTip.show(response.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    @Override
    protected void onclick() {
        bind.tvDeleteProect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        bind.tvAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c,SuperWorkerInfoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        bind.tvSetManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            setManager();
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