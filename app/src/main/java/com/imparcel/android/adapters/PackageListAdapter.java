package com.imparcel.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.imparcel.android.models.Package;

import com.imparcel.android.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sjlu on 6/30/15.
 */
public class PackageListAdapter extends BaseAdapter {

    Context context;
    List<Package> pkgs;
    Activity activity;
    private static LayoutInflater inflater = null;

    public PackageListAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

        this.pkgs = Package.listAll(Package.class);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.pkgs.size();
    }

    @Override
    public Object getItem(int i) {
        return this.pkgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private void setView(final View view, final Package pkg) {
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // get view properties
                TextView title = (TextView) view.findViewById(R.id.name);
                TextView subtitle = (TextView) view.findViewById(R.id.text);

                title.setText(pkg.getName());
                subtitle.setText(pkg.status);
            }
        });

    }

    public void refresh() {
        this.pkgs = Package.listAll(Package.class);
        this.notifyDataSetChanged();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final PackageListAdapter packageListAdapter = this;
        final Package pkg = pkgs.get(i);

        if (view == null) {
            view = inflater.inflate(R.layout.package_row, null);
        }
        final View finalView = view;

        // Set view properties of currently cached model
        packageListAdapter.setView(view, pkg);

        // lets make a async request on updating the view
        String url = "http://imparcel.com/api/tracking/" + pkg.tracking_code;
        if (pkg.carrier != null) {
            url = url + "?" + pkg.carrier;
        }

        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call =  httpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {}

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    JSONObject packageData = null;
                    packageData = new JSONObject(response.body().string());

                    pkg.name = packageData.getString("name_formatted");
                    pkg.carrier = packageData.getString("carrier");
                    pkg.status = packageData.getString("status_formatted");

                    pkg.save();
//                    finalView.invalidate();

                    packageListAdapter.setView(finalView, pkg);
                }
                catch (JSONException e) {
                    Log.e("PackageListAdapter", null, e);
                }
            }
        });

        return view;
    }
}
