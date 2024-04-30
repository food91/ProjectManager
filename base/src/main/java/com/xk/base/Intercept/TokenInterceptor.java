package com.xk.base.Intercept;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.tencent.mmkv.MMKV;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    public static String token = "";
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        if(TextUtils.isEmpty(token)){
            token = MMKV.defaultMMKV().getString("token","");
        }
        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer " + token); // 将token添加到请求头部
        Request request = builder.build();
        return chain.proceed(request);
    }
}
