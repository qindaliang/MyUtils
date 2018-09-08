package com.qin.myutils.mvp_gericity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qin.myutils.R;
import com.qin.myutils.mvp.ui.LoginSuccessActivity;
import com.qin.myutils.mvp_gericity.base.UserContract;
import com.qin.myutils.mvp_gericity.presenter.UserPreenter;
import com.qin.myutils.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GernericityActivity extends AppCompatActivity implements UserContract.View {

    UserPreenter mUserPreenter;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        mUserPreenter = new UserPreenter(this);
    }

    @Override
    public void showLoginDialog() {
        ToastUtils.show(this,"showLoginDialog" );
    }

    @Override
    public void hideLoginDialog() {
        ToastUtils.show(this,"hideLoginDialog" );
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
        startActivity(new Intent(GernericityActivity.this,LoginSuccessActivity.class));
    }

    @Override
    public void loginFailure() {
        ToastUtils.show(this,"loginFailure" );
    }

    @Override
    public void showError() {

    }

    @OnClick(R.id.btn)
    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.btn:
                mUserPreenter.login();
                break;
        }
    }
}
