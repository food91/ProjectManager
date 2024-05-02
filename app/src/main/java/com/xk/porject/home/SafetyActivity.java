package com.xk.porject.home;

import android.content.Intent;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.datepicker.CalendarDialog;
import com.kongzue.dialogx.datepicker.interfaces.OnDateSelected;
import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.PostSafeTest;
import com.xk.base.data.Response;
import com.xk.base.data.ResponseProjectPostInfo;
import com.xk.base.data.ResponseSafeQuestion;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.R;
import com.xk.porject.utils.Utils;
import com.xk.porject.utils.VerificationCodeHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class SafetyActivity extends BaseActivityPortrait<com.xk.porject.databinding.ActivitySafetyBinding> {

    private List<testData> list_1;
    private List<testData> list2_2;


    @Override
    protected void initPortraitView() {

    }

    @Override
    protected void initData() {
        list_1 = new ArrayList<>();
        list2_2=new ArrayList<>();
        list_1.add(new testData(bind.edInputTop,bind.cbTop));
        list2_2.add(new testData(bind.edInputBottom,bind.cbBottom));
        addclicklist(list_1);
        addclicklist(list2_2);
    }

    private void addclicklist(List<testData> list) {
// 为每个CheckBox设置监听器
        for (int i = 0; i < list.size(); i++) {
            final int index = i; // 捕获当前索引
            list.get(i).ischeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // 遍历列表，将所有其他CheckBox设置为未选中状态
                        for (int j = 0; j < list_1.size(); j++) {
                            if (j != index) { // 排除当前选中的CheckBox
                                list.get(j).ischeck.setChecked(false);
                            }
                        }
                    }
                }
            });
        }
    }

    private void Commit(){
        String time = bind.tvTime.getText().toString();
        String title = bind.edTitle.getText().toString();
        String test = bind.edTest.getText().toString();
        String score = bind.edInputScore.getText().toString();
        if(!Utils.areAllStringsValid(time,title,test,score)){
            PopTip.show("输入不能有空");
            return;
        }
        PostSafeTest postSafeTest =new PostSafeTest();
        for(int i=0;i<list_1.size();i++){
            String text =list_1.get(i).tile.getText().toString();
            if(!Utils.areAllStringsValid(test)){
                PopTip.show("输入不能有空");
                return;
            }
         postSafeTest.setSA(text);
         if(list_1.get(i).ischeck.isChecked()){
             postSafeTest.setSKey(i+"");
         }
        }
        postSafeTest.setScore(Integer.parseInt(score));
        postSafeTest.setName(title);
        postSafeTest.setScontent(test);
        postSafeTest.setRemark(time);
        List<PostSafeTest> postlist = new ArrayList<>();
        postlist.add(postSafeTest);
        PostSafeTest postSafeTest2= new PostSafeTest();
        for(int i=0;i<list2_2.size();i++){
            String text =list2_2.get(i).tile.getText().toString();
            if(!Utils.areAllStringsValid(test)){
                PopTip.show("输入不能有空");
                return;
            }
            postSafeTest.setSA(text);
            if(list2_2.get(i).ischeck.isChecked()){
                postSafeTest.setSKey(i+"");
            }
        }
        postlist.add(postSafeTest2);
        performApiCall(ApiClient.getClient().create(ApiService.class).postSafeTest(postlist),
                new Consumer<ResponseSafeQuestion>() {
            @Override
            public void accept(ResponseSafeQuestion response) throws Exception {
                    Intent intent =new Intent(c, ExamBamkActivity.class);
                    intent.putExtra("date",time);
                    startActivity(intent);
                    finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private void openCalendar(){
        CalendarDialog.build().show(new OnDateSelected() {
            @Override
            public void onSelect(String s, int i, int i1, int i2) {
                        bind.spinner.setText(s);
            }
        });
    }

    @Override
    protected void onclick() {
        bind.spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar();
            }
        });
        bind.tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commit();
            }
        });
        bind.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bind.addRvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_1.size()<4){
                    addViewToLayout(bind.llRv1,list_1);
                }
            }
        });
        bind.addRvBomttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_1.size()<4){
                    addViewToLayout(bind.llRv2,list2_2);
                }
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SafetyActivity.this, ExamBamkActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addViewToLayout(LinearLayout parentLayout,List<testData> list) {
        // 实例化新布局
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_safe_test, null);
        // 可以对新布局的视图元素进行配置，例如设置文本等
        // 将新布局添加到父布局中
        EditText ed = itemView.findViewById(R.id.ed_input1);
        CheckBox cb = itemView.findViewById(R.id.cb_1);
        addclicklist(list);
        testData t =new testData(ed,cb);
        list.add(t);
        ImageView iv2 = itemView.findViewById(R.id.iv_delete);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(t);
                addclicklist(list);
               parentLayout.removeView(itemView);
            }
        });
        parentLayout.addView(itemView);
    }


    class testData{
        public EditText tile;
        public CheckBox ischeck;

        public testData(EditText ed, CheckBox cb) {
            this.tile=ed;
            this.ischeck = cb;
        }

    }
}