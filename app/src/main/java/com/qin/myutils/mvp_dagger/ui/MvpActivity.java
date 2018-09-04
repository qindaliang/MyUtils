package com.qin.myutils.mvp_dagger.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.qin.myutils.R;
import com.qin.myutils.mvp_dagger.di.component.DaggerUserPreComponent;
import com.qin.myutils.mvp_dagger.di.module.UserPreModule;
import com.qin.myutils.mvp_dagger.presenter.UserPresenter;
import com.qin.myutils.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn)
    Button btn;

    @Inject
    UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        DaggerUserPreComponent.builder().userPreModule(new UserPreModule(this)).build().inject(this);
    }

    @Override
    public void showLoginDialog() {
        ToastUtils.show(this, "showLoginDialog");
    }

    @Override
    public void hideLoginDialog() {
        ToastUtils.show(this, "hideLoginDialog");
    }

    @Override
    public String getUserName() {
        return etName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPwd.getText().toString().trim();
    }

    @Override
    public void toSuccessAct() {
        startActivity(new Intent(MvpActivity.this, LoginSuccessActivity.class));
    }

    @Override
    public void loginFailure() {
        ToastUtils.show(this, "loginFailure");
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        mUserPresenter.login();
    }
}
