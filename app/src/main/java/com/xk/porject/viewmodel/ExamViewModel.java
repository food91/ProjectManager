package com.xk.porject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.ResponseExamResponse;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import io.reactivex.functions.Consumer;

public class ExamViewModel extends BaseViewModel {

    private MutableLiveData<ResponseExamResponse> responseExamResponseMutableLiveData =new MutableLiveData<>();
    public LiveData<ResponseExamResponse> getExamBankData(){
        return responseExamResponseMutableLiveData;
    }

    public void getExam(){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getExam(), new Consumer<ResponseExamResponse>() {
            @Override
            public void accept(ResponseExamResponse response) throws Exception {
                    if(response.getCode()==200){
                        responseExamResponseMutableLiveData.postValue(response);
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
}
