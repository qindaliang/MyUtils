package com.qin.myutils.room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qin.myutils.R;

import butterknife.ButterKnife;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
    }

}
