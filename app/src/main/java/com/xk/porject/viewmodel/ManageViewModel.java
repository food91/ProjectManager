package com.xk.porject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.DictCode;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseFindProjectList;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.data.ResponseWorker;
import com.xk.base.data.WithdrawaData;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import java.util.List;

import io.reactivex.functions.Consumer;

public class ManageViewModel extends BaseViewModel {

    public int search=0;
    public String projectname;
    private MutableLiveData<ResponseFindProjectList.Data> data = new MutableLiveData<>();
    public MutableLiveData<List<DictCode.Data>> workStateString = new MutableLiveData<>();


    public MutableLiveData<ResponseFindProjectList.Data> getGroupInfo() {
        return data;
    }

    public void getData(int id){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getProjectWork(id), new Consumer<ResponseFindProjectList>() {
            @Override
            public void accept(ResponseFindProjectList responseWorker) throws Exception {
                if(responseWorker.getCode()==200){
                    data.postValue(responseWorker.getData());
                }else{
                    PopTip.show(responseWorker.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void getWorkState(){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getWorkStateInfo(), new Consumer<DictCode>() {
            @Override
            public void accept(DictCode response) throws Exception {
                if(response.getCode()==200){
                    workStateString.postValue(response.getData());
                }else {
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

}
