package com.xk.porject.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VerificationCodeHelper {

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable countdownRunnable;
    private int time = 60; // 验证码倒计时时间，默认为60秒
    private TextView codeLabel; // 假设你有一个TextView用来显示验证码和倒计时

    public VerificationCodeHelper(TextView codeLabel) {
        this.codeLabel = codeLabel;
    }

    public Disposable requestAndStartCountdown(String phone, ApiClient.ResponseCallback callback) {
        // 发送验证码请求
        Disposable disposable = ApiClient.getClient().create(ApiService.class)
                .getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (response.getCode() == 200) {
                                callback.onSuccess(response);
                                startCountdown(); // 验证码发送成功后开始倒计时
                            } else {
                                PopTip.show(response.getMsg());
                            }
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            PopTip.show("验证码获取失败");
                            callback.onError(throwable);
                        }
                );
        return disposable;
    }

    private void startCountdown() {
        codeLabel.setClickable(false);
        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (time > 0) {
                    codeLabel.setText(String.valueOf(time));
                    time--;
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
                    codeLabel.setClickable(true);
                    codeLabel.setText("验证码");
                    time = 60; // 重置倒计时时间
                }
            }
        };
        handler.post(countdownRunnable);
    }


    public void cancelCountdown() {
        handler.removeCallbacks(countdownRunnable);
        codeLabel.setClickable(true);
        codeLabel.setText("验证码");
        time = 60; // 重置倒计时时间
    }
}
