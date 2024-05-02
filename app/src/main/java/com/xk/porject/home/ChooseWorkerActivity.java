package com.xk.porject.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.xk.base.data.AddGoupData;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;
import com.xk.porject.adapter.ExpandableListAdapter;
import com.xk.porject.contractor.WorkerInfoActivity;
import com.xk.porject.databinding.ActivityChooseWorkerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import io.reactivex.functions.Consumer;

public class ChooseWorkerActivity extends BaseActivityPortrait<ActivityChooseWorkerBinding> {
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
        bind.tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose();
            }
        });

        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void choose(){
        Intent returnIntent = new Intent();
        // 添加返回的数据到Intent中，例如使用putExtra方法添加键值对
        returnIntent.putExtra("key_data", deletelist.toString());
        // 设置结果码为RESULT_OK，并附带上包含数据的Intent对象
        setResult(Activity.RESULT_OK, returnIntent);
        // 结束这个Activity，返回到上一个Activity
        finish();
    }


    @Override
    protected void initPortraitView() {
        bind.rv.setLayoutManager(new LinearLayoutManager(this));

    }
}