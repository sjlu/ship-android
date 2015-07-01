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
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sjlu on 6/30/15.
 */
public class Package extends SugarRecord<Package> {

    public String tracking_code;
    public String carrier;
    public String status;
    public String name;

    public Package() {}

    public Package(String tracking_code) {
        Log.e("Input", tracking_code);
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

}

