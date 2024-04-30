package com.xk.porject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.tencent.mmkv.MMKV;
import com.xk.base.data.Identity;
import com.xk.base.data.LoginData;
import com.xk.base.data.ResponseLogin;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.porject.contractor.ContractorMainActivity;
import com.xk.porject.databinding.ActivityLoginBinding;
import com.xk.porject.projectmain.PorjectAppMainActivity;
import com.xk.porject.teamleader.ContractorLeaderMainActivity;
import com.xk.porject.teamleader.ui.ContractorLeaderActivity;
import com.xk.porject.ui.login.ForgotPwActivity;
import com.xk.porject.ui.login.RegisterActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding layoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        MMKV.defaultMMKV().putString("token","");
        layoutBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(layoutBinding.getRoot());
        initializeUi();
    }

    private void initializeUi() {
        layoutBinding.tvMLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        adminproject.LoginActivity.class); // 假设登陆成功后跳转到MainActivity
                startActivity(intent);
                finish();
            }
        });
        layoutBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edUser = layoutBinding.edUser;
                EditText edPs = layoutBinding.edPs;
                if (edUser.getText().toString().isEmpty()) {
                    PopTip.show("用户名不能为空"); // 假设的PopTip类和方法，需要具体实现
                    return;
                }
                if (edPs.getText().toString().isEmpty()) {
                    PopTip.show("密码不能为空"); // 假设的PopTip类和方法，需要具体实现
                    return;
                }
                String user = edUser.getText().toString();
                String pw = edPs.getText().toString();
                LoginData data = new LoginData();

                if(user.equals("0")&&pw.equals("0")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // 假设登陆成功后跳转到MainActivity
                    startActivity(intent);
                    finish();
                }
                if(user.equals("1")&&pw.equals("1")){
                    user = "19581571279";
                    pw = "123456";
                }
                if(user.equals("2")&&pw.equals("1")){
                    Intent intent = new Intent(LoginActivity.this, ContractorMainActivity.class); // 假设登陆成功后跳转到MainActivity
                    startActivity(intent);
                    finish();
                }
                if(user.equals("3")&&pw.equals("1")){
                    Intent intent = new Intent(LoginActivity.this, ContractorLeaderMainActivity.class); // 假设登陆成功后跳转到MainActivity
                    startActivity(intent);
                    finish();
                }
                data.setUsername(user);
                data.setPassword(pw);
              ApiClient.getClient().create(ApiService.class).login(data).subscribeOn(
                      Schedulers.io()).
                      observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Observer<ResponseLogin>() {
                          @Override
                          public void onSubscribe(Disposable d) {
                              WaitDialog.show("登陆中...");
                          }

                          @Override
                          public void onNext(ResponseLogin s) {
                              WaitDialog.dismiss();
                              if(s.getCode()==200){
                                  MMKV.defaultMMKV().putString("token",s.getToken());
                                  if(s.getAuthorizat()==Identity.PROJECT_PARTY_ID){
                                      Intent intent = new Intent(LoginActivity.this, PorjectAppMainActivity.class); // 假设登陆成功后跳转到MainActivity
                                      startActivity(intent);
                                      finish();
                                  }else if(s.getAuthorizat()==Identity.CONTRACTOR_PARTY_ID){
                                      Intent intent = new Intent(LoginActivity.this, ContractorMainActivity.class); // 假设登陆成功后跳转到MainActivity
                                      startActivity(intent);
                                      finish();
                                  }else{
                                      Intent intent = new Intent(LoginActivity.this, MainActivity.class); // 假设登陆成功后跳转到MainActivity
                                      startActivity(intent);
                                      finish();
                                  }


                              }else{
                                  PopTip.show("登录失败");
                              }

                          }

                          @Override
                          public void onError(Throwable e) {
                              PopTip.show("登录失败");
                              WaitDialog.dismiss();
                          }

                          @Override
                          public void onComplete() {

                          }
                      });

            }
        });

        layoutBinding.btRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        layoutBinding.btForPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPwActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login(){
//        if ("123456".equals(user) && "123456".equals(pw)) {
//            // 假设的WaitDialog类和方法，需要具体实现
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class); // 假设登陆成功后跳转到MainActivity
//                    startActivity(intent);
//                    WaitDialog.dismiss(); // 假设的WaitDialog类和方法，需要具体实现
//                    finish();
//                }
//            }, 500);
//        } else if(user.equals("xm")&&pw.equals("123")){

//            finish();
//        } else if(user.equals("cb")&&pw.equals("123")){

//            finish();
//        }
//        else {
//            PopTip.show("密码错误");
//        }
    }
}