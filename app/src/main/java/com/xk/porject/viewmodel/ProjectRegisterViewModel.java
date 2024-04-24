package com.xk.porject.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xk.base.data.ProjectPartyData;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectRegisterViewModel extends ViewModel {

    private MutableLiveData<String> msg;

    public void CreatePorjectParty(String type, String code, ProjectPartyData data){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.PostProjectPartyData(type,code,data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
