package com.xk.porject.home;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.orhanobut.logger.Logger;
import com.xk.base.data.AddGoupData;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.adapter.ExpandableListAdapter;

import com.xk.porject.contractor.WorkerInfoActivity;
import com.xk.porject.data.ExpandableListItem;
import com.xk.porject.databinding.ActivityWorkManageBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WorkManageActivity extends BaseActivityPortrait<ActivityWorkManageBinding> {

    List<GroupInfo.Datum> data = new ArrayList<>();
    List<GroupInfo.Datum> path;
    GroupInfo.Datum postionDatum=new GroupInfo.Datum();
    List<GroupInfo.Datum> deletelist;

    private int mode;

    @Override
    protected void initData() {
        mode=getIntent().getIntExtra("mode",0);
        path=new ArrayList<>();
        deletelist = new ArrayList<>();
        QueryGroup(null);
    }

    private void setAdapter(){
        adapter = new ExpandableListAdapter(data, new ExpandableListAdapter.OnItemBindListener() {
            @Override
            public void show(@NonNull ExpandableListAdapter.ViewHolder holder, GroupInfo.Datum item, int position) {
                holder.title.setText(item.getGroupName());
                holder.checkBox.setChecked(false);
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(mode==1){
                          showMessage(item);
                            return;
                        }
                        if(isChecked){
                            deletelist.add(item);
                        }else{
                            deletelist.remove(item);
                        }
                    }
                });
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        QueryGroup(item);
                    }
                });
            }
        });
        bind.rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showMessage(GroupInfo.Datum item){
        MessageDialog.show("选择分组","你确定选择"+item.getGroupName(),"确定","取消")
                .setOkButton(new OnDialogButtonClickListener<MessageDialog>() {
                    @Override
                    public boolean onClick(MessageDialog messageDialog, View view) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("id",item.getId());
                        returnIntent.putExtra("name",item.getGroupName());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                        return false;
                    }
                }).show();
    }

    private void QueryGroup(GroupInfo.Datum item){
        String request ="";
        if(item==null){
            request="0";
        }else{
            request = item.getId()+"";
        }
        performApiCall(ApiClient.getClient().create(ApiService.class).getgroup(request),
                new Consumer<GroupInfo>() {
                    @Override
                    public void accept(GroupInfo groupInfo) throws Exception {
                        postionDatum=item;
                            data=groupInfo.getData();
                            if(postionDatum!=null){
                                path.add(postionDatum);
                                setPath();
                            }
                            setAdapter();
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
        performApiCall(ApiClient.getClient().create(ApiService.class).getgroup(path.get(queryid).getGroupValue()),
                new Consumer<GroupInfo>() {
                    @Override
                    public void accept(GroupInfo groupInfo) throws Exception {
                        data=groupInfo.getData();
                        for(int i=0;i<data.size();i++){
                            if(data.get(i).getId()==Integer.parseInt(postionDatum.getGroupValue())){
                                postionDatum = data.get(i);
                            }
                        }
                        path.remove(path.size()-1);
                        setPath();
                        adapter.setData(data);
                        adapter.notifyDataSetChanged();
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
            add_data.setGroupValue("0");
        }else{
            add_data.setGroupValue(postionDatum.getId()+"");
        }
        performApiCall(ApiClient.getClient().create(ApiService.class)
                .Addgroup(add_data), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                QueryGroup(postionDatum);
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
                if(deletelist==null||deletelist.isEmpty()){
                    PopTip.show("请选中重命名对象");
                    return;
                }
                if(deletelist.size()>1){
                    PopTip.show("重命名只能选择一个");
                    return;
                }
                reName(deletelist.get(0));
            }
        });
        bind.tvMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deletelist==null||deletelist.isEmpty()){
                    PopTip.show("请选择移动对象");
                    return;
                }
                if(deletelist.size()>1){
                    PopTip.show("移动只能选择一个对象");
                    return;
                }
                move(deletelist.get(0));
            }
        });
    }

    private void move(GroupInfo.Datum item){
        List<String> strlist = new ArrayList<>();
        strlist.add("移动到父节点");
        List<GroupInfo.Datum> temp = new ArrayList<>();
        for(int i=0;i<data.size();i++){
            if(data.get(i).getId()==deletelist.get(0).getId()){
                continue;
            }
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("移动到");
            stringBuffer.append(data.get(i).getGroupName());
            temp.add(data.get(i));
            stringBuffer.append("子节点");
            strlist.add(stringBuffer.toString());
        }
        String[] strArray = strlist.toArray(new String[0]);
        BottomMenu.show(strArray).setMessage("移动").setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
            @Override
            public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                if(i==0){
                    if(postionDatum==null){
                        PopTip.show("已经在最上层节点");
                        return false;
                    }
                    move(item.getId(),postionDatum.getGroupValue());
                }else{
                    move(item.getId(),temp.get(i-1).getId()+"");
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
                        data.removeIf(new Predicate<GroupInfo.Datum>() {
                            @Override
                            public boolean test(GroupInfo.Datum datum) {
                                if(datum.getId()==id){
                                    return true;
                                }
                                return false;
                            }
                        });
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

    private void reName(GroupInfo.Datum item){

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

    private void httprename(String input,GroupInfo.Datum item){
        performApiCall(ApiClient.getClient().create(ApiService.class).Rename(item.getId(),input)
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
        if(deletelist.size()>1){
            PopTip.show("分组不能批量删除");
            return;
        }
        if(deletelist.isEmpty()){
            PopTip.show("请选择删除的组");
            return;
        }
        performApiCall(ApiClient.getClient().create(ApiService.class).deletegroup(deletelist.get(0).getId()+""), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                    if(response.getCode()==200){
                        PopTip.show("删除成功");
                        data.remove(deletelist.get(0));
                        deletelist.clear();
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
                    deletelist.clear();
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

    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));

    }
}