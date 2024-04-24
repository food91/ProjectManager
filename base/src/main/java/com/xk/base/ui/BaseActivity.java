package com.xk.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T bind;
    protected Context c;
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
