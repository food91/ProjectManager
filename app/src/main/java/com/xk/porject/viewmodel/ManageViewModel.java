package com.xk.porject.viewmodel;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.utils.BaseViewModel;

import io.reactivex.functions.Consumer;

public class ManageViewModel extends BaseViewModel {

    public int search=0;
    public String projectname;
    public void getData(int id){
        executeNetworkRequest(ApiClient.getClient().create(ApiService.class).getProjectDetail(id), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                    if(response.getCode()==200){

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
