package com.xk.base.net;



import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.Intercept.TokenInterceptor;
import com.xk.base.data.Response;
import com.xk.base.data.StockData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static final String     BASE_URL = "https://szjjytest.mynatapp.cc";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(loggingInterceptor)
                    .addInterceptor(new TokenInterceptor())
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    public interface ResponseCallback {
        void onSuccess(Response response);
        void onError(Throwable e);
    }


    public static Disposable requestCode(String phone, ResponseCallback callback) {
        Disposable disposable= ApiClient.getClient().create(ApiService.class)
                .getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response.getCode() == 200) {
                                callback.onSuccess(response);
                            } else {
                                PopTip.show(response.getMsg());
                            }
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            PopTip.show("验证码获取失败");
                            callback.onError(throwable);
                        }
                );
        return disposable;
    }
}
