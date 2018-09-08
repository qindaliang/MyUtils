package com.qin.myutils.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qin.myutils.MyApplication;
import com.qin.myutils.R;
import com.qin.myutils.greendao.data.Shop;
import com.qin.myutils.greendao.data.UserInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {
private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.insert, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                count ++;
                MyApplication.getInstance().getDaoSeesion().getUserInfoDao().insert(new UserInfo("15"+count, "qin"+count, null));
                UserInfo userInfo = new UserInfo("15" + count, "qin" + count, null);
                MyApplication.getInstance().getDaoSeesion().getShopDao().insert(new Shop("鞋"+count,count,"yiban"+count,10L,userInfo));
                break;
            case R.id.query:
                List<UserInfo> userInfos = MyApplication.getInstance().getDaoSeesion().getUserInfoDao().loadAll();
                List<Shop> shops = MyApplication.getInstance().getDaoSeesion().getShopDao().loadAll();
                for (int i = 0;i<userInfos.size();i++){
                    Log.i("userinfo",userInfos.get(i).toString() );
                } for (int i = 0;i<shops.size();i++){
                    Log.i("userinfo",shops.get(i).toString() );
                }
                break;
        }
    }
}
