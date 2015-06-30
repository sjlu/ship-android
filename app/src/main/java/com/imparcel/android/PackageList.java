package com.imparcel.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sjlu on 6/30/15.
 */
public class PackageList extends BaseAdapter {

    Context context;
    String[] data;
    private static LayoutInflater inflater = null;

    public PackageList(Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.package_row, null);
        }
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(data[i]);
        return view;
    }
}
