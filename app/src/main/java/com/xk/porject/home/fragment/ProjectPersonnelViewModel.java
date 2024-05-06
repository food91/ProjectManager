package com.xk.porject.home.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.GroupInfo;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ProjectPersonnelViewModel extends BaseViewModel {

    // 使用 MutableLiveData 而不是 LiveData，因为我们需要在 ViewModel 内部更新数据
    private MutableLiveData<GroupInfo.Data> groupInfo = new MutableLiveData<>();

    // 这是一个 LiveData 对象，用于外部观察数据变化
    public LiveData<GroupInfo.Data> getGroupInfo() {
        return groupInfo;
    }

    private void getGroup(int id) {
        executeNetworkRequest(
                ApiClient.getClient().create(ApiService.class).getgroupWork(id),
                new Consumer<GroupInfo>() {
                    @Override
                    public void accept(GroupInfo groupInfoResponse) throws Exception {
                        if(groupInfoResponse.getCode()==200){
                            groupInfo.setValue(groupInfoResponse.getData());
                        }else {
                            PopTip.show(groupInfoResponse.getMsg());
                        }
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        // 可以在这里处理错误，例如通过设置一个错误消息
                    }
                }
        );
    }
}