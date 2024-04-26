package com.xk.base.net;




import com.xk.base.data.AddContractData;
import com.xk.base.data.AddGoupData;
import com.xk.base.data.AddProjectData;
import com.xk.base.data.AddWorker;
import com.xk.base.data.FeedData;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.LoginData;
import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseLogin;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    @PUT("/system/user/profile/updatePwd")
    public Observable<Response> UpdatePW(@Query("oldPassword") String pw,@Query("newPassword") String pw1,@Query("newPwd") String pw2);

    @GET("/system/signin/verification/{phone}")
    public Observable<Response> getCode(@Path("phone") String phone);
    @GET("/project/group/find/{value}")
    public Observable<GroupInfo> getgroup(@Path("value") String value);
    @GET("/project/group/remove/{id}/{value}")
    public Observable<GroupInfo> Rename(@Path("id") int id,@Path("value") String value);
    @GET("/project/group/remove/{value}")
    public Observable<Response> deletegroup(@Path("value") String value);
    @POST("/project/group")
    public Observable<Response> Addgroup(@Body AddGoupData value);

    @GET("/project/group/move/{id}/{value}")
    public Observable<Response> RemoveGroup(@Path("id")int id,@Path("value")String value);
    @Multipart
    @POST("/common/upload")
    public Observable<Response> upLoad(@Part MultipartBody.Part  file);
    @POST("/project/projects")
    public Observable<Response> addNewProject(@Body AddProjectData phone);
    @GET("/system/signin/forgot/{phone}/{code}")
    public Observable<Response> getForgot(@Path("phone") String phone,@Path("code") String code);
    @POST("/system/signin/sign/{type}/{verificat}")
    public Observable<Response> PostProjectPartyData(@Body List<ProjectPartyData> data, @Path("type") int type,
                                                     @Path("verificat")String code
                                                     );
    @POST("/project/contract")
    public Observable<Response> AddContract(@Body AddContractData data);
    @POST("/project/feedback")
    public Observable<Response> SendFeedBack(@Body FeedData data);
    @POST("/login")
    public Observable<ResponseLogin> login(@Body LoginData data);

    @POST("/project/supervisor")
    public Observable<Response> addWorker(@Body AddWorker data);
}
