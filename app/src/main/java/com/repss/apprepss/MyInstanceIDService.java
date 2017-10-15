package com.repss.apprepss;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Refreshing GCM Registration Token
 */
public class MyInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyInstanceIDService";

    /**
     * Refreshing GCM Registration Token
     */
    @Override
    public void onTokenRefresh() {

        Log.d(TAG, "Refreshing GCM Registration Token");

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

}
