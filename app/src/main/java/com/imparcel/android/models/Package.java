package com.imparcel.android.models;

import android.util.Log;

import com.orm.SugarRecord;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by sjlu on 6/30/15.
 */
public class Package extends SugarRecord<Package> {

    String tracking_code;
    String carrier;
    String status;
//    OkHttpClient httpClient = new OkHttpClient();


    public Package() {}

    public Package(String tracking_code) {
        this.tracking_code = tracking_code;
    }

    public Package(String tracking_code, String carrier) {
        this.tracking_code = tracking_code;
        this.carrier = carrier;
    }

    public void getTrackingDetails() throws IOException {

        String url = "http://imparcel.com/api/tracking/" + this.tracking_code;
//        if (!this.carrier.isEmpty()) {
//            url = url + "?" + this.carrier;
//        }

        Log.e("url", url);

//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Response response = httpClient.newCall(request).execute();
//        System.out.println(response.body().string());


    }

}

