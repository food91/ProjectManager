package com.xk.porject.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.xk.base.data.JobPostData;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityAddInformationBinding;

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

public class AddInformationActivity extends BaseActivityPortrait<ActivityAddInformationBinding> {

    private String path;
    private String[] str_state={"在建","完工"};
    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.idImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicture();
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    bind.btSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            postJobInfor();
        }
    });

    }

    private void postJobInfor(){
        String name = bind.edName.getText().toString();
        String str_native = bind.edPhone.getText().toString();
        String   place=bind.edPlace.getText().toString();
        String   state =bind.edState.getText().toString();
        String   show=bind.edShow.getText().toString();
        String quest = bind.edRequirements.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(str_native)||TextUtils.isEmpty(place)
                ||TextUtils.isEmpty(state)||TextUtils.isEmpty(show)
        ||TextUtils.isEmpty(quest)){
            PopTip.show("输入不能有空项");
            return;
        }
        if(path==null){
            PopTip.show("请上传图片");
            return;
        }
        JobPostData data =new JobPostData();
        data.setProjectName(name);
        data.setProjectAddress(place);
        data.setPhone(str_native);
        data.setCompletionDate(state);
        data.setImages(path);
        data.setTechnologyDemand(quest);
        data.setStatus(bind.spState.getSelectedIndex());
        data.setDemand(show);
        performApiCall(ApiClient.getClient().create(ApiService.class).addInformation(data), new Consumer<Response>() {
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

    @Override
    protected void initPortraitView() {
        List<String> dataset = new LinkedList<>(Arrays.asList(str_state));
        bind.spState.attachDataSource(dataset);
    }
}