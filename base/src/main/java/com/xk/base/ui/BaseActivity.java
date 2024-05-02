package com.xk.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;


import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T bind;
    protected Context c;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void onclick();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = getViewBinding();
        c=this;
        setContentView(bind.getRoot());
        initView();
        initData();
        onclick();
    }
    protected <T> void performApiCall(Observable<T> observable, Consumer<T> onSuccess, Consumer<Throwable> onError) {
        showLoadingDialog("正在处理请求...");
        Disposable disposable=observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            dismissLoadingDialog();

                            onSuccess.accept(response);
                        },
                        throwable -> {
                            dismissLoadingDialog();
                            PopTip.show("连接失败");
                            throwable.printStackTrace();
                            onError.accept(throwable);
                        }
                );
        compositeDisposable.add(disposable);
    }

    protected void showLoadingDialog(String message) {
        WaitDialog.show(message);
    }

    protected void dismissLoadingDialog() {
       WaitDialog.dismiss();
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    private T getViewBinding() {
        T bind = null;
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
            Method inflate = clazz.getDeclaredMethod("inflate", LayoutInflater.class);
            bind = (T) inflate.invoke(null, getLayoutInflater());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bind;
    }

}
