package com.xk.porject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import io.reactivex.functions.Consumer;

public class ProjectManageViewModel extends BaseViewModel {
    private MutableLiveData<ResponseFindlist> projectListLiveData = new MutableLiveData<>();


    public void initdata() {
        getProjectlist();
    }

    public void getContractorList(int id){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getContracttList(),
                new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                            PopTip.show("连接失败");
                            throwable.printStackTrace();
                    }
                });
    }


    public void getProjectlist() {
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).findlistproect(),
                new Consumer<ResponseFindlist>() {
                    @Override
                    public void accept(ResponseFindlist response) throws Exception {
                        if (response.getCode() == 200) {
                            projectListLiveData.postValue(response);
                        } else {
                            PopTip.show(response.getMsg());
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


    public LiveData<ResponseFindlist> getProjectListLiveData() {
        return projectListLiveData;
    }

}
