package com.imparcel.android.models;

import android.util.Log;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by sjlu on 6/30/15.
 */
public class Package extends SugarRecord<Package> {

    public String tracking_code;
    public String carrier;
    public String status;
    public String name;
    public long delivery_date;

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

    public String getStatus() {

        String builtStatus = "Package status unavailable";

        if (this.status != null) {
            builtStatus = "";
            if (this.status.equalsIgnoreCase("in_transit")) {
                builtStatus += "In Transit";

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                long now = calendar.getTime().getTime();
                long timeDiff = this.delivery_date - now;
                int dayDiff = (int) Math.ceil(timeDiff / (1000 * 60 * 60 * 24)) + 1;

                if (dayDiff > 0) {
                    builtStatus += " - " + dayDiff + " day";
                    if (dayDiff > 1) {
                        builtStatus += "s";
                    }
                    builtStatus += " left";
                }
            } else if (this.status.equalsIgnoreCase("out_for_delivery")) {
                builtStatus += "Out For Delivery Today";
            } else if (this.status.equalsIgnoreCase("delivered")) {
                builtStatus += "Delivered";
            } else if (this.status.equalsIgnoreCase("invalid")) {
                builtStatus = "Tracking code invalid";
            }
        }

        return builtStatus;


    }

}

