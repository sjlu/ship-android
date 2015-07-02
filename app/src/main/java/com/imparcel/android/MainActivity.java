package com.imparcel.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.imparcel.android.models.Package;
import com.imparcel.android.adapters.PackageListAdapter;

import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.List;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {

    ListView listview;
    PackageListAdapter packageListAdapter;
    BroadcastReceiver mMessageBroadcastReceiver;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshview);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                packageListAdapter.refresh();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        packageListAdapter = new PackageListAdapter(this, this);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(packageListAdapter);

        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

        mMessageBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                packageListAdapter.refresh();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageBroadcastReceiver, new IntentFilter("messageReceived"));
    }

    public void openAddActivity(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            packageListAdapter.refresh();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        packageListAdapter.refresh();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 9000).show();
            } else {
                Log.i("MainActivity", "This device does not support Google Play services");
                finish();
            }
            return false;
        }
        return true;
    }

}
