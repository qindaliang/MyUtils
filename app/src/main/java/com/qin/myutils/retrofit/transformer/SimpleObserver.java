package com.qin.myutils.retrofit.transformer;


import android.text.TextUtils;
import android.widget.Toast;
import com.qin.myutils.utils.ContextUtils;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author jlanglang  2016/11/14 17:32
 * @版本 2.0
 * @Change
 */
public abstract class SimpleObserver<T> implements Observer<T> {
    protected Disposable mDisposable;
    private CompositeDisposable mCompositeDisposable;

    public SimpleObserver() {
        this(null);
    }

    public SimpleObserver(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
        try {
            mCompositeDisposable.add(d);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onNext(T t) {
        call(t);
    }

    @Override
    public void onError(Throwable e) {
        showErrorMsg(e);
    }

//    private boolean isDisposed() {
//        return mCompositeDisposable != null && !mDisposable.isDisposed() && !mCompositeDisposable.isDisposed();
//    }

    @Override
    public void onComplete() {

    }

    /**
     * 默认提示
     * @param e
     */
    @Deprecated
    public void showErrorMsg(Throwable e) {
        if (!TextUtils.isEmpty(e.getMessage())) {
            Toast.makeText(ContextUtils.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public abstract void call(@NonNull T t);
}
