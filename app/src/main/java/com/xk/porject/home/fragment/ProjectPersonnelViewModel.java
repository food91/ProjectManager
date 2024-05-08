package com.xk.porject.home.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseAllWorkData;
import com.xk.base.data.WageRequest;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import java.util.List;

import io.reactivex.functions.Consumer;

public class ProjectPersonnelViewModel extends BaseViewModel {


    private MutableLiveData<List<ResponseAllWorkData.Data.Project>> dataInfo = new MutableLiveData<>();


    public LiveData<List<ResponseAllWorkData.Data.Project>> getDataInfo() {
        return dataInfo;
    }

    public void PostWage(WageRequest wageRequest){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).postWage(), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void init(){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getAllWorker(), new Consumer<ResponseAllWorkData>() {
            @Override
            public void accept(ResponseAllWorkData response) throws Exception {
                    if(response.getCode()==200){
                        dataInfo.postValue(response.getData().getProject());
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


}