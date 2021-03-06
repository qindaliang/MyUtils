package com.qin.myutils.retrofit.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.qin.myutils.retrofit.base.presenter.BasePresenter;

/**
 *
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements UIView {
    //public String TAG = this.getClass().getSimpleName();tag不要用反射的形式取
    protected T mPresenter;
    private SparseArray<View> mViews;
    private View mContentView;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = initPresenter();
        //绑定Activity
        mPresenter.onAttach(this);
        //初始化ContentView
        mContentView = initView(getLayoutInflater(), savedInstanceState);
        if (mContentView != null) {
            super.setContentView(mContentView);
        }
    }

    @Override
    public void onNewThrowable(Throwable throwable) {

    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //初始化Activity
        init(savedInstanceState);
        //初始化presenter
        mPresenter.onCreate();
        onPresentersCreate();

        mPresenter.onRefreshData();
    }

    protected void initStatusBar() {

    }


    /**
     * 扩展除了默认的presenter的其他Presenter初始化
     */
    protected void onPresentersCreate() {

    }

    /**
     * 运行在initView之后
     * 已经setContentView
     * 可以做一些初始化操作
     *
     * @return
     */
    protected void init(@Nullable Bundle savedInstanceState) {

    }

    @Override
    @Nullable
    public View getContentView() {
        return mContentView != null ? mContentView : findViewById(android.R.id.content);
    }

    @Override
    protected void onStart() {
        mPresenter.onStart();
        super.onStart();
    }

    @Override
    protected void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        mPresenter.onRestart();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        mPresenter.onDetach();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * 跳转fragment
     *
     * @param tofragment
     */
    @Override
    public void startFragment(Fragment tofragment) {
        startFragment(tofragment, null);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag) {
        startFragment(fragment, tag, true);
    }

    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, boolean isAdd) {

    }


    /**
     * @param fragment 跳转的fragment
     * @param tag      fragment的标签
     */
    @Override
    public void startFragment(Fragment fragment, String tag, int enter, int popExit) {
        startFragment(fragment, tag, enter, popExit, true);
    }

    @Override
    public void startFragment(Fragment fragment, String tag, int enter, int popExit, boolean isAddBack) {
        startFragment(fragment, tag,
                enter,
                0,
                0,
                popExit, isAddBack);
    }

    @Override
    public void startFragment(Fragment fragment, String tag, int enterAnim, int exitAnim, int popEnter, int popExit, boolean isAddBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);
        fragmentTransaction.add(android.R.id.content, fragment, tag);
        if (isAddBack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    /**
     * @param rootFragment Activity内部fragment
     * @param containerId  fragment父容器
     */
    public void replaceFragment(Fragment rootFragment, int containerId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, rootFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, Bundle bundle, int flag) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        intent.addFlags(flag);
        startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void startActivity(Class aClass, int flag) {
        Intent intent = new Intent(this, aClass);
        intent.addFlags(flag);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public BaseActivity getAppcompatActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return this;
    }

    private SparseArray<View> getViews() {
        if (mViews == null) {
            mViews = new SparseArray<>();
        }
        return mViews;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId 资源id
     * @return
     */
    @Override
    public <V extends View> V findView(@IdRes int viewId) {
        View view = getViews().get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            getViews().put(viewId, view);
        }
        return (V) view;
    }


    /**
     * 初始化ContentView
     * 建议不要包含toolbar
     *
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    protected View initView(@NonNull LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        int layout = initView(savedInstanceState);
        try {
            return inflater.inflate(layout, null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isFinish() {
        return isFinishing();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    /**
     * 建议不要包含toolbar
     *
     * @param savedInstanceState
     * @return 布局layout
     */
    @LayoutRes
    protected abstract int initView(@Nullable Bundle savedInstanceState);

    /**
     * 子类实现Presenter,且必须继承BasePresenter
     *
     * @return
     */
    protected abstract T initPresenter();


}
