/*
 * Copyright 2015 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qin.myutils.realm.thread.ui;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qin.myutils.R;
import com.qin.myutils.realm.thread.model.Personer;

import io.realm.Realm;


public class ReceivingActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);

        realm = Realm.getDefaultInstance();

        if (getIntent() != null) {
            String personId = getIntent().getStringExtra("person_id");
            if (personId != null) {
                Personer person = realm.where(Personer.class).equalTo("id", personId).findFirst();
                ((TextView) findViewById(R.id.received_content)).setText(String.format("Received person_id and loaded: %s", person.toString()));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
