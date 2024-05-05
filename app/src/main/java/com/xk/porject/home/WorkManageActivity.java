package com.xk.porject.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.xk.base.data.AddGoupData;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.adapter.ExpandableListAdapter;

import com.xk.porject.databinding.ActivityWorkManageBinding;
import com.xk.porject.viewmodel.WorkManageViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import io.reactivex.functions.Consumer;

public class WorkManageActivity extends BaseActivityPortrait<ActivityWorkManageBinding> {


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
        viewModel.getProjectListLiveData().observe(this, new Observer<ResponseFindlist>() {
            @Override
            public void onChanged(ResponseFindlist responseFindlist) {
                if(!responseFindlist.getData().isEmpty()){
                    data = new GroupInfo.Data();
                    List<GroupInfo.Group> group =new ArrayList<>();
                    for(int i=0;i<responseFindlist.getData().size();i++){
                        ResponseFindlist.Datum item = responseFindlist.getData().get(i);
                        GroupInfo.Group group1 =new GroupInfo.Group();
                        group1.setid(0);
                        group1.setGroupValue("-1");
                        group1.setGroupName(item.getProjectName());
                        group.add(group1);
                    }
                    data.setGroup(group);
                    adapter.setData(data);
                    bind.tvPath.setText("/");
                    path.clear();
                }
            }
        });
        viewModel.getProjectlist();
        //    viewModel.getGroupList();
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
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QueryGroup(id,item);
                    }
                });
            }

            @Override
            public void showWork(@NonNull ExpandableListAdapter.ViewHolder holder, GroupInfo.Worker item, int position) {

            }

        });
        bind.rv.setAdapter(adapter);
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

    private void QueryGroupBack(){
        int queryid = 0;
        if(path.size()-2<0){
            queryid = 0;
        }else{
            queryid = path.size()-1;
        }
        String value = path.get(queryid).getGroupValue();
        if(value.equals("-1")){
            viewModel.getProjectlist();
        }else {
            performApiCall(ApiClient.getClient().create(ApiService.class).getgroup(path.get(queryid).getGroupValue(),id),
                    new Consumer<GroupInfo>() {
                        @Override
                        public void accept(GroupInfo groupInfo) throws Exception {
                            data=groupInfo.getData();
                            path.remove(path.size()-1);
                            postionDatum = path.get(path.size()-1);
                            setPath();
                            adapter.setData(data);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                            WaitDialog.dismiss();
                        }
                    });
        }

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


    private void add(){
        String[] str ={"添加分组","添加职员"};
        BottomMenu.show(str)
                .setMessage("添加")
                .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                    @Override
                    public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                        if(i==0){
                            addGourp();
                        }else if(i==1){
                            Intent intent =new Intent(WorkManageActivity.this, WorkerInfoActivity.class);
                            intent.putExtra("mode",1);
                            startActivity(intent);
                        }
                        return false;
                    }
                });
    }

    private  void  addGourp(){
        new InputDialog("添加分组", "请输入组名", "确定", "取消", "")
                .setCancelable(false)
                .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                    @Override
                    public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                        httpAddGroup(inputStr);
                        return false;
                    }
                })
                .show();
    }

    private void httpAddGroup(String name){
        AddGoupData add_data = new AddGoupData();
        add_data.setGroupName(name);
        if(postionDatum==null){
            PopTip.show("该页不能添加分组");
            return;
        }else{
            add_data.setGroupValue(postionDatum.getid()+"");
        }
        add_data.setPid(id);
        performApiCall(ApiClient.getClient().create(ApiService.class)
                .Addgroup(add_data), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                QueryGroup(id,postionDatum);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
    ExpandableListAdapter adapter;
    @Override
    protected void onclick() {
        bind.ivUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(path==null||path.isEmpty()){
                    bind.tvPath.setText("/");
                    return;
                }
                QueryGroupBack();
            }
        });
        bind.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGroup();
            }
        });
        bind.tvRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = deletelist.getGroup().size()+deletelist.getWorker().size();
                if(deletelist==null||deletelist.getGroup()==null||deletelist.getGroup().isEmpty()){
                    PopTip.show("请选中重命名对象");
                    return;
                }
                if(sum>1){
                    PopTip.show("重命名只能选择一个");
                    return;
                }
                if(deletelist.getGroup().get(0).getGroupValue().equals("-1")){
                    PopTip.show("项目不能重命名");
                    return;
                }
                reName(deletelist.getGroup().get(0));
            }
        });
        bind.tvMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deletelist==null||deletelist.getGroup()==null||deletelist.getGroup().isEmpty()){
                    PopTip.show("请选择移动对象");
                    return;
                }
                if(deletelist.getGroup().size()>1){
                    PopTip.show("移动分组为单项选择");
                    return;
                }
                move(deletelist.getGroup().get(0));
            }
        });
    }

    private void move(GroupInfo.Group item){
        if(item.getGroupValue().equals("-1")){
            PopTip.show("项目不允许移动");
            return;
        }
        List<String> strlist = new ArrayList<>();
        strlist.add("移动到父节点");
        List<GroupInfo.Group> temp = new ArrayList<>();
        for(int i=0;i<data.getGroup().size();i++){
            if(data.getGroup().get(i).getid()==deletelist.getGroup().get(0).getid()){
                continue;
            }
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("移动到");
            stringBuffer.append(data.getGroup().get(i).getGroupName());
            temp.add(data.getGroup().get(i));
            stringBuffer.append("子节点");
            strlist.add(stringBuffer.toString());
        }
        String[] strArray = strlist.toArray(new String[0]);
        BottomMenu.show(strArray).setMessage("移动").setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
            @Override
            public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                if(i==0){
                    if(postionDatum==null||postionDatum.getGroupValue().equals("-1")){
                        PopTip.show("已经在最上层节点");
                        return false;
                    }
                    move(item.getid(),postionDatum.getGroupValue()+"");
                }else{
                    move(item.getid(),temp.get(i-1).getid()+"");
                }
                return false;
            }
        });
    }

    private void move(int id,String value){
        performApiCall(ApiClient.getClient().create(ApiService.class).moveGroup(
                id, value
        ), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    data.getGroup().removeIf(new Predicate<GroupInfo.Group>() {
                        @Override
                        public boolean test(GroupInfo.Group group) {
                            if(group.getid()==id){
                                return true;
                            }
                            return false;
                        }
                    });
                    adapter.notifyDataSetChanged();
                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                PopTip.show("连接失败");
                throwable.printStackTrace();
            }
        });
    }

    private void reName(GroupInfo.Group item){

        new InputDialog("重命名", "请输入新名称", "确定", "取消", "")
                .setCancelable(false)
                .setOkButton(new OnInputDialogButtonClickListener<InputDialog>() {
                    @Override
                    public boolean onClick(InputDialog baseDialog, View v, String inputStr) {
                        httprename(inputStr,item);
                        return false;
                    }
                })
                .show();
    }

    private void httprename(String input,GroupInfo.Group item){
        performApiCall(ApiClient.getClient().create(ApiService.class).Rename(item.getid(),input)
                , new Consumer<Response>() {
                    @Override
                    public void accept(Response groupInfo) throws Exception {
                        if(groupInfo.getCode()==200){
                            item.setGroupName(input);
                            adapter.notifyDataSetChanged();
                        }else{
                            PopTip.show(groupInfo.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        PopTip.show("连接失败");
                    }
                });
    }

    private void deleteGroup(){
        for(int i=0;i<deletelist.getGroup().size();i++){
            if(deletelist.getGroup().get(i).getGroupValue().equals("-1")){
                PopTip.show(deletelist.getGroup().get(i).getGroupName()+"不能删除，它是一个项目");
                continue;
            }
            httpdeleteGroup(deletelist.getGroup().get(i).getid()+"");
        }

    }

    private void httpdeleteGroup(String id){
        performApiCall(ApiClient.getClient().create(ApiService.class).deletegroup(id), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    PopTip.show("删除成功");
                    data.getGroup().remove(deletelist.getGroup().get(0));
                    deletelist.getGroup().clear();
                    adapter.notifyDataSetChanged();
                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }



    private void Deletelist(){
        String commaSeparatedIds = String.join(",", deletelist.toString());
        performApiCall(ApiClient.getClient().create(ApiService.class).deletegroup(commaSeparatedIds), new Consumer<Response>() {
            @Override
            public void accept(Response groupInfo) throws Exception {
                if (groupInfo.getCode() == 200) {
                    PopTip.show("删除成功");
                    adapter.notifyDataSetChanged();
                } else {
                    PopTip.show(groupInfo.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }


}