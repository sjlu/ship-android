package com.imparcel.android.models;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by sjlu on 6/30/15.
 */
public class Package extends SugarRecord<Package> {

    public static final String TAG = Package.class.getSimpleName();

    String tracking_code;
    String carrier;
    String status;
    String name;
    @Ignore
    OkHttpClient httpClient = new OkHttpClient();


    public Package() {}

    public Package(String tracking_code) {
        this.tracking_code = tracking_code;
    }

    public Package(String tracking_code, String carrier) {
        this.tracking_code = tracking_code;
        this.carrier = carrier;
    }

    public String getName() {

        if (this.name != null) {
            return this.name;
        }

        String name = "";
        if (this.carrier != null) {
            name = name + this.carrier + " ";
        }
        name = name + this.tracking_code;

        return name;
    }

    public String getStatus() {
        return this.status;
    }

    private void storeResponse(String jsonData) throws JSONException {
        JSONObject packageData = null;
        packageData = new JSONObject(jsonData);
        this.name = packageData.getString("name_formatted");
        this.carrier = packageData.getString("carrier");
        this.status = packageData.getString("status_formatted");
        this.save();
    }

    public void getTrackingDetails() {
        Package pkg = this;

        String url = "http://imparcel.com/api/tracking/" + this.tracking_code;
        if (this.carrier != null) {
            url = url + "?" + this.carrier;
        }

        Log.e("url", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call =  httpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    storeResponse(response.body().string());
                }
                catch (JSONException e) {
                    Log.e(TAG, null, e);
                }
            }
        });

    }

}

