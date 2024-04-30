package com.xk.porject.contractor;

import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.net.Uri;
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
import com.kongzue.albumdialog.PhotoAlbumDialog;
import com.kongzue.albumdialog.util.DialogImplCallback;
import com.kongzue.albumdialog.util.SelectPhotoCallback;
import com.kongzue.dialogx.dialogs.FullScreenDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.orhanobut.logger.Logger;
import com.xk.base.data.AddWorker;
import com.xk.base.data.Response;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.base.utils.MyData;
import com.xk.porject.R;
import com.xk.porject.databinding.ActivityWorkerInfoBinding;
import com.xk.porject.home.WorkManageActivity;

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

public class WorkerInfoActivity extends BaseActivityPortrait<ActivityWorkerInfoBinding> {

    private String path;
    private int groupid=0;
    List<String> years = new ArrayList<>();
    List<String> months = new ArrayList<>();
    List<String> days = new ArrayList<>();
    List<String> type = new ArrayList<>();
    List<String> type_time = new ArrayList<>();
    ArrayAdapter<String> adapterYear,adapterMonth,adapterDay,adapterTimeType,adapterType;
    ActivityResultContracts.StartActivityForResult contract = new ActivityResultContracts.StartActivityForResult();
    ActivityResultCallback<ActivityResult> callBack = new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()== Activity.RESULT_OK){
                Intent data = result.getData();
                if(data!=null){
                    groupid = data.getIntExtra("id",0);
                    String name = data.getStringExtra("name");
                    bind.tvChooseGrounp.setText(name);
                    bind.tvChooseGrounp.setTextColor(ResourcesCompat.getColor(getResources(),
                            R.color.purple_500, null));
                }

            }
        }
    };

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
    private ActivityResultLauncher<Intent> mActivityBLauncher = registerForActivityResult(contract, callBack);
    private void chooseGroup(){
        Intent intent = new Intent(c,WorkManageActivity.class);
        intent.putExtra("mode",1);
        mActivityBLauncher.launch(intent);
    }

    @Override
    protected void onclick() {

        bind.tvChooseGrounp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            chooseGroup();
            }
        });
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
        bind.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        bind.llTypeFinal.setVisibility(View.VISIBLE);
                        bind.llTypeCount.setVisibility(View.GONE);
                    }else if(position==1){
                        bind.llTypeFinal.setVisibility(View.GONE);
                        bind.llTypeCount.setVisibility(View.VISIBLE);
                    }else if(position==2){
                        bind.llTypeFinal.setVisibility(View.VISIBLE);
                        bind.llTypeCount.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bind.checkboxCheckAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bind.checkboxCheckIn.setChecked(false);
                }
            }
        });
        bind.checkboxCheckIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bind.checkboxCheckAuto.setChecked(false);
                }
            }
        });
        initAdapter();
    }

    private void addWorkCheck(){
        AddWorker addWorker = new AddWorker();
        if(TextUtils.isEmpty(path)){
            PopTip.show("请上传图片");
            return;
        }
        String str_group = bind.tvChooseGrounp.getText().toString();
        if(TextUtils.isEmpty(str_group)){
            PopTip.show("请选择分组");
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
        }else{
            addWorker.setSex(0);
        }
        String str_home = bind.edHome.getText().toString();
        if(TextUtils.isEmpty(str_home)){
            PopTip.show("请输入籍贯");
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
        String selectedItem = (String) bind.spinnerType.getSelectedItem();
        if(TextUtils.isEmpty(selectedItem)){
            PopTip.show("请选择工资种类");
            return;
        }
        addWorker.setWageType(bind.spinnerType.getSelectedItemPosition());
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
        String month = bind.spinnerMon.getSelectedItem().toString();
        String day = bind.spinnerDay.getSelectedItem().toString();
        String time = year + "-" + month + "-" + day;
        addWorker.setOnbordTime(MyData.getData());
        boolean check = bind.checkboxCheckAuto.isChecked();
        if(check){
        addWorker.setCheckoutType(0);
        }else{
            addWorker.setCheckoutType(1);
        }
        String phone = bind.edPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            PopTip.show("账号/手机号不能为空");
            return;
        }
        addWorker.setUserName(phone);
        addWorker.setPassword("123456");
        boolean unexpected = bind.checkboxUnexpected.isChecked();
        if(!unexpected){
            PopTip.show("请勾选意外险");
            return;
        }
        boolean unboducheck = bind.checkboxUnbody.isChecked();
        if(!unboducheck){
            PopTip.show("请确认已经体检");
            return;
        }
        addWorker.setCreateTime(MyData.getData());
        addWorker.setImg(path);
        performApiCall(ApiClient.getClient().create(ApiService.class).addWorker(addWorker), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){
                    PopTip.show("添加成功");
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
        bind.spinnerType.setAdapter(adapterType);

        adapterTimeType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type_time);
        adapterTimeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bind.spTypeTime.setAdapter(adapterTimeType);
    }

    @Override
    protected void initPortraitView() {

    }
}