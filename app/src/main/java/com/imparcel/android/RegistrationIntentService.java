package com.imparcel.android;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by sjlu on 7/2/15.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegistrationIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            synchronized (TAG) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i(TAG, "Token obtained: " + token);

                obtainNotificationIdFromToken(token);
            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to refresh token", e);
        }
    }

    private void obtainNotificationIdFromToken(String token) {
        // send this to the server
    }



}
