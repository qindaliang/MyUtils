package com.qin.myutils.retrofit.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *rxjava添加Dialog
 */
public class LoadingTransformer<T> implements ObservableTransformer<T, T> {
    private LoadingInterface mLoading;

    public LoadingTransformer(@NonNull LoadingInterface loading) {
        mLoading = loading;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mLoading.onLoading();
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mLoading.onError();
                    }
                }).doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (t == null) {
                            mLoading.onEmpty();
                        }
                        mLoading.onSuccess();
                    }
                });
    }

    public interface LoadingInterface {
        void onLoading();

        void onSuccess();

        void onError();

        void onEmpty();
    }
}
