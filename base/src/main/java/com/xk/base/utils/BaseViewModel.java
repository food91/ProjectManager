package com.xk.base.utils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xk.base.log.X;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // 添加Disposable以便管理订阅和防止内存泄漏
    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    // 执行网络请求
    protected  <T> void   executeNetworkRequest(Observable<T> observable,
                                         Consumer<T> onSuccess,
                                         Consumer<Throwable> onError) {
        Disposable disposable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (onSuccess != null) {
                                onSuccess.accept(response);
                            }
                        },
                        throwable -> {
                            if (onError != null) {
                                onError.accept(throwable);
                            }
                        }
                );
        addDisposable(disposable);
    }

    // 在ViewModel不再需要时，清理所有订阅
    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}