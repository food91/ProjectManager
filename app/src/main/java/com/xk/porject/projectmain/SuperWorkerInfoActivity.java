package com.xk.porject.projectmain;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kongzue.albumdialog.PhotoAlbumDialog;
import com.kongzue.albumdialog.util.DialogImplCallback;
import com.kongzue.albumdialog.util.SelectPhotoCallback;
import com.kongzue.dialogx.dialogs.FullScreenDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.xk.base.data.AddWorker;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivitySuperworkerInfoBinding;
import com.xk.porject.databinding.ActivityWorkerInfoBinding;
import com.xk.porject.home.ChooseWorkerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SuperWorkerInfoActivity extends BaseActivityPortrait<ActivitySuperworkerInfoBinding> {

    private String path;
    private int groupid=0;
    List<String> years = new ArrayList<>();
    List<String> months = new ArrayList<>();
    List<String> days = new ArrayList<>();
    List<String> type = new ArrayList<>();
    List<String> type_time = new ArrayList<>();
    ArrayAdapter<String> adapterYear,adapterMonth,adapterDay,adapterTimeType,adapterType;
    private int cid;
    private int pid;


    @Override
    protected void initData() {
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > Calendar.getInstance().get(Calendar.YEAR) - 10; i--) {
            years.add(String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        type.add("固定工资（记工）");
        type.add("计数工资（计件）");
        type.add("工资+计数（记工+计件）");
        type_time.add("月");
        type_time.add("日");
        type_time.add("年");
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
        bind.tvAddworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWorkCheck();
            }
        });


        initAdapter();
   //     resaveData();
    }

    private void resaveData(){
        String addWorkerJson = MMKV.defaultMMKV().getString("workinfo", "");
        if(TextUtils.isEmpty(addWorkerJson)){
            return;
        }
        AddWorker addWorker2 = new Gson().fromJson(addWorkerJson, AddWorker.class);
        path =addWorker2.getImg();
        bind.ivAdd.setVisibility(View.GONE);
        bind.image2.setVisibility(View.VISIBLE);
        Glide.with(c).load(path).centerCrop().
                into(bind.image2);
        groupid = addWorker2.getGroupId();
        pid = addWorker2.getpId();
        bind.edWorkType.setText(addWorker2.getWageType());
        bind.edName.setText(addWorker2.getName());
        String sex ="";
        if(addWorker2.getSex()==0){
            sex="男";
        }else{
            sex="女";
        }
        bind.edSex.setText(sex);
        bind.edNation.setText(addWorker2.getFamilyName());
        bind.edNation.setText(addWorker2.getFamilyName());
        bind.edAge.setText(addWorker2.getAge()+"");
        bind.edIdent.setText(addWorker2.getEmployId());
        if(  bind.llTypeFinal.getVisibility()==View.VISIBLE){
            bind.edTypeMoney.setText(addWorker2.getWage()+"");
        }
        if(bind.llTypeCount.getVisibility()==View.VISIBLE){
            bind.edTypeCountMoney.setText(addWorker2.getWage()+"");
        }
        bind.edBankAccountNumber.setText(addWorker2.getBankAccountNumber());
        bind.edBankDeposit.setText(addWorker2.getBankDeposit());
        String dateTimeString =  addWorker2.getOnbordTime();
        String[] parts = dateTimeString.split(" ");
        String datePart = parts[0];
        String[] dateComponents = datePart.split("-");

        String year = dateComponents[0];
        String month = dateComponents[1];
        String day = dateComponents[2];
        for(int i=0;i<adapterDay.getCount();i++){
            if(adapterDay.getItem(i).equals(day)){
                bind.spinnerDay.setSelection(i);
                break;
            }
        }
        for(int i=0;i<adapterYear.getCount();i++){
            if(adapterDay.getItem(i).equals(year)){
                bind.spinnerYear.setSelection(i);
                break;
            }
        }
        for(int i=0;i<adapterYear.getCount();i++){
            if(adapterDay.getItem(i).equals(month)){
                bind.spinnerMon.setSelection(i);
                break;
            }
        }
        bind.edPhone.setText(addWorker2.getUserName());
    }

    private void queryScore(){
       PopTip.show("暂时没有该职员评分");
    }

    private void addWorkCheck(){
        AddWorker addWorker = new AddWorker();
        if(TextUtils.isEmpty(path)){
            PopTip.show("请上传图片");
            return;
        }
        addWorker.setGroupId(groupid);
        String str_work = bind.edWorkType.getText().toString();
        if(TextUtils.isEmpty(str_work)){
            PopTip.show("请选择工种");
            return;
        }
        addWorker.setEmployId(str_work);
        String str_name = bind.edName.getText().toString();
        if(TextUtils.isEmpty(str_name)){
            PopTip.show("请输入姓名");
            return;
        }
        addWorker.setName(str_name);
        String str_sex = bind.edSex.getText().toString();
        if(TextUtils.isEmpty(str_sex)){
            PopTip.show("请输入性别");
            return;
        }
        if(str_sex.equals("女")){
            addWorker.setSex(1);
        }else if(str_sex.equals("男")){
            addWorker.setSex(0);
        }else {
            PopTip.show("请正确填写性别");
            return;
        }
        String str_nation = bind.edNation.getText().toString();
        if(TextUtils.isEmpty(str_nation)){
            PopTip.show("请输入民族");
            return;
        }
        addWorker.setFamilyName(str_nation);
        String str_age = bind.edAge.getText().toString();
        if(TextUtils.isEmpty(str_age)){
            PopTip.show("请输入年龄");
            return;
        }
        addWorker.setAge(Integer.parseInt(str_age));
        String str_id = bind.edIdent.getText().toString();
        if(TextUtils.isEmpty(str_id)){
            PopTip.show("请输入身份证号");
            return;
        }
        if(  bind.llTypeFinal.getVisibility()==View.VISIBLE){
            String money = bind.edTypeMoney.getText().toString();
            String mon = bind.edTypeDay.getText().toString();
            String select = bind.spTypeTime.getSelectedItem().toString();
            if(TextUtils.isEmpty(money)||TextUtils.isEmpty(mon)||TextUtils.isEmpty(select)){
                PopTip.show("固定工资输入项不能为空");
                return;
            }
            addWorker.setWage(Integer.parseInt(money));
        }
        if(bind.llTypeCount.getVisibility()==View.VISIBLE){
            String money2 = bind.edTypeCountMoney.getText().toString();
            String count2 = bind.edTypeCountCount.getText().toString();
            String type2 = bind.edTypeCountName.getText().toString();
            if(TextUtils.isEmpty(money2)||TextUtils.isEmpty(count2)||TextUtils.isEmpty(type2)){
                PopTip.show("固定工资输入项不能为空");
                return;
            }
            addWorker.setPieceworkWage(Integer.parseInt(money2));
        }
        String bankAccountNumber = bind.edBankAccountNumber.getText().toString();
        if(TextUtils.isEmpty(bankAccountNumber)){
            PopTip.show("银行账号不能为空");
            return;
        }
        addWorker.setBankAccountNumber(bankAccountNumber);
        String bankDeposit = bind.edBankDeposit.getText().toString();
        if(TextUtils.isEmpty(bankDeposit)){
            PopTip.show("开户行不能为空");
            return;
        }
        addWorker.setBankDeposit(bankDeposit);
        String year = bind.spinnerYear.getSelectedItem().toString();
        String rawMonth = bind.spinnerMon.getSelectedItem().toString();
        String rawDay = bind.spinnerDay.getSelectedItem().toString();
        // 确保月份和日期是两位数，如果不是，在前面补零
        String month = (rawMonth.length() == 1) ? "0" + rawMonth : rawMonth;
        String day = (rawDay.length() == 1) ? "0" + rawDay : rawDay;
        String hour = "00";
        String minute = "00";
        String second = "00";
        String dateTimeString = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        addWorker.setOnbordTime(dateTimeString);
        String phone = bind.edPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("账号/手机号不能为空");
            return;
        }
        addWorker.setUserName(phone);
        String pw = bind.edPw.getText().toString();
        if(TextUtils.isEmpty(pw)){
            PopTip.show("请输入密码");
            return;
        }
        addWorker.setPassword("123456");
        addWorker.setpId(pid);
        addWorker.setCreateTime(MyData.getData());
        addWorker.setImg(path);
        performApiCall(ApiClient.getClient().create(ApiService.class).addWorker(addWorker), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    PopTip.show("添加成功");
                    MMKV.defaultMMKV().putString("workinfo",new Gson().toJson(addWorker));
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

    private void initAdapter(){
// 设置适配器
        adapterYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spinnerYear.setAdapter(adapterYear);

        adapterMonth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spinnerMon.setAdapter(adapterMonth);

        adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spinnerDay.setAdapter(adapterDay);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Calendar月份是从0开始的
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        bind.spinnerYear.setSelection(years.indexOf(String.valueOf(currentYear)));
        bind.spinnerMon.setSelection(months.indexOf(String.valueOf(currentMonth)));
        bind.spinnerDay.setSelection(days.indexOf(String.valueOf(currentDay)));
        adapterType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTimeType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_time);
        adapterTimeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spTypeTime.setAdapter(adapterTimeType);
    }

    @Override
    protected void initPortraitView() {

    }
}