package com.xk.porject.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.kongzue.albumdialog.PhotoAlbumDialog;
import com.kongzue.albumdialog.util.DialogImplCallback;
import com.kongzue.albumdialog.util.SelectPhotoCallback;
import com.kongzue.dialogx.dialogs.FullScreenDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.orhanobut.logger.Logger;
import com.xk.base.data.JobInfomation;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityJobInformationBinding;
import com.xk.porject.utils.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class JobInformationActivity extends BaseActivityPortrait<ActivityJobInformationBinding> {

    private String path;
    private String[] workstate={"在职","离职"};
    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        bind.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }

    private void commit(){
        String name = bind.edName.getText().toString();
        String str_native = bind.edNative.getText().toString();
        String   place=bind.edPlace.getText().toString();
        String   state =bind.edState.getText().toString();
        String   show=bind.edShow.getText().toString();
        String phone = bind.edPhone.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(str_native)||TextUtils.isEmpty(place)
        ||TextUtils.isEmpty(state)||TextUtils.isEmpty(show)||
        TextUtils.isEmpty(phone)){
            PopTip.show("输入不能有空项");
            return;
        }
        if(path==null){
            PopTip.show("请上传图片");
            return;
        }
        JobInfomation infomation = new JobInfomation();
        infomation.setImage(path);
        infomation.setName(name);
        infomation.setNativePlace(str_native);
        infomation.setWorkerStatus(Utils.getWorkState(bind.spState.getSelectedIndex()));
        infomation.setStatus(0);
        infomation.setPhone(phone);
        infomation.setConcrete(state);
        infomation.setStrongPoint(show);
        performApiCall(ApiClient.getClient().create(ApiService.class).addJobInformation(infomation), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    PopTip.show(response.getMsg());
                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                PopTip.show("连接失败");
            }
        });
    }

    @Override
    protected void initPortraitView() {
        List<String> dataset = new LinkedList<>(Arrays.asList(workstate));
        bind.spState.attachDataSource(dataset);
        bind.idImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicture();
            }
        });
    }
    private void openPicture(){
        PhotoAlbumDialog.build()
                .setCompressQuality(100)     //开启压缩并指定质量 90%
                .setCompressPhoto(false)    //是否压缩（开启回调格式为 jpg，不开启回调格式为 png）
                .setMaxSelectPhotoCount(1)
                .setClip(false)              //开启裁剪模式
                .setMaxSize(1000)           //最高分辨率 1000（宽或高大于 1000会被等比缩小到 1000内）
                .setMaxWidth(300)          //最大宽度
                .setMaxHeight(300)         //最大高度
                .setCallback(new SelectPhotoCallback() {
                    //单张模式回调（二者其一任选）
                    @Override
                    public void selectedPhoto(String selectedPhotos) {
                        upload(selectedPhotos);
                        Logger.d(selectedPhotos);
                    }
                })
                .setDialogDialogImplCallback(new DialogImplCallback<FullScreenDialog>() {
                    @Override
                    public void onDialogCreated(FullScreenDialog dialog) {

                    }


                })
                .show(this);
    }

    private void upload(String s){
        File file = new File(s);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/octet-stream"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        ApiClient.getClient().create(ApiService.class)
                .upLoad(filePart).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Logger.d(response.toString());
                        if(response.getCode()==200){
                            path = response.getUrl();
                            bind.ivAdd.setVisibility(View.GONE);
                            bind.image2.setVisibility(View.VISIBLE);
                            Glide.with(c).load(path).centerCrop().
                                    into(bind.image2);
                        }else{
                            PopTip.show(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}