package com.xk.base.net;




import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseLogin;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @POST("/dev-api/project/contract")
    public  Observable<String> addContract();

    @GET("/system/signin/verification/")
    public Observable<Response> getCode(@Query("phone") String phone);

    @POST("//system/signin/sign/{type}/{verificat}")
    public Observable<String> PostProjectPartyData(@Path("type") String phone,@Path("verificat")String code,

                                      ProjectPartyData data);
    @FormUrlEncoded
    @POST("/login")
    public Observable<ResponseLogin> login(@Field("username") String phone, @Field("password") String pw);
}
