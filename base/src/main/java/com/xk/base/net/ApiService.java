package com.xk.base.net;




import com.xk.base.data.AddContractData;
import com.xk.base.data.AddGoupData;
import com.xk.base.data.AddProjectData;
import com.xk.base.data.AddWorker;
import com.xk.base.data.FeedData;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.JobInfomation;
import com.xk.base.data.JobPostData;
import com.xk.base.data.LoginData;
import com.xk.base.data.PostSafeTest;
import com.xk.base.data.PostScheduleData;
import com.xk.base.data.ProjectPartyData;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseFindWork;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.data.ResponseLogin;
import com.xk.base.data.ResponseProjectPostInfo;
import com.xk.base.data.ResponseSafeQuestion;
import com.xk.base.data.StockData;
import com.xk.base.data.WithdrawaData;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @GET("/project/group/remove/{id}/{name}")
    public Observable<Response> Rename(@Path("id") int id,@Path("name") String targatname);
    @GET("/project/group/remove/{value}")
    public Observable<Response> deletegroup(@Path("value") String value);
    @POST("/project/group")
    public Observable<Response> Addgroup(@Body AddGoupData value);

    @POST("/project/group")
    public Observable<Response> addStock(@Body StockData value);
    @GET("/project/stock/{id}")
    public Observable<Response> getStock(@Path("id")int id);

    @DELETE("/project/stock/{ids}")
    public Observable<Response> deleteStock(@Path("id")int ids);
    @GET("/project/group/move/{id}/{value}")
    public Observable<Response> moveGroup(@Path("id") int id, @Path("value") String value);
    @Multipart
    @POST("/common/upload")
    public Observable<Response> upLoad(@Part MultipartBody.Part  file);
    @POST("/project/projects")
    public Observable<Response> addNewProject(@Body AddProjectData phone);

    @POST("/project/projectparty/findlist")
    public Observable<ResponseFindlist> findlistproect();

    @GET("/project/jobinformation/fuzzy/{type}")
    public Observable<Response> fuzzy(@Path("type") int type);
    @GET("/project/jobinformation/list")
    public Observable<ResponseFindWork> getJobList();
    @GET("/project/information/list")
    public Observable<ResponseProjectPostInfo> getProectList();
    @POST("/project/supervisor/card/{id}")
    public Observable<Response> getWordCard(@Path("id") int id);
    @GET("/system/signin/forgot/{phone}/{code}")
    public Observable<Response> getForgot(@Path("phone") String phone,@Path("code") String code);

    @GET("/project/purchase/list")
    public Observable<Response> getpurchaselist();
    @POST("/system/signin/sign/{type}/{verificat}")
    public Observable<Response> PostProjectPartyData(@Body List<ProjectPartyData> data, @Path("type") int type,
                                                     @Path("verificat")String code
                                                     );
    @GET("/project/projectparty/{id}")
    public Observable<Response> getProjectDetail(@Path("id") int id);
    @GET("/project/contract/list")
    public Observable<Response> getContracttList();
    @POST("/project/contract")
    public Observable<Response> AddContract(@Body AddContractData data);
    @POST("/project/feedback")
    public Observable<Response> SendFeedBack(@Body FeedData data);
    @POST("/login")
    public Observable<ResponseLogin> login(@Body LoginData data);

    @POST("/project/supervisor")
    public Observable<Response> addWorker(@Body AddWorker data);
    @POST("/project/jobinformation")
    public Observable<Response> addJobInformation(@Body JobInfomation data);
    @POST("/project/information")
    public Observable<Response> addInformation(@Body JobPostData data);

    @POST("/project/schedule")
    public Observable<Response> addScheduleData(@Body PostScheduleData data);

    @POST("/project/withdrawa")
    public Observable<Response> addWithdrawa(@Body WithdrawaData data);

    @POST("/project/topic")
    public Observable<ResponseSafeQuestion> postSafeTest(@Body List<PostSafeTest> data);

    @GET("/project/examination/list")
    public Observable<Response> getexamination();

    @GET("/project/wage/list")
    public Observable<Response> getWage();

    @GET("/project/topic/list")
    public Observable<Response> gettestlist();

}
