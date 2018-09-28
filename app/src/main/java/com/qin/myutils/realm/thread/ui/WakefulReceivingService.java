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

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.qin.myutils.realm.thread.model.Personer;

import io.realm.Realm;


public class WakefulReceivingService extends IntentService {

    public WakefulReceivingService() {
        super("WakefulReceivingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.getExtras() != null) {
            String personId = intent.getStringExtra("person_id");
            Realm realm = Realm.getDefaultInstance();
            Personer person = realm.where(Personer.class).equalTo("id", personId).findFirst();
            final String output = person.toString();
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Loaded Personer from broadcast-receiver->intent-service: " + output, Toast.LENGTH_LONG).show();
                }
            });
            realm.close();
        }

        // All the work is done, release the wake locks/etc
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
