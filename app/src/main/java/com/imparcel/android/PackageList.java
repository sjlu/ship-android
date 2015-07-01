package com.imparcel.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.imparcel.android.models.Package;

import java.util.List;

/**
 * Created by sjlu on 6/30/15.
 */
public class PackageList extends BaseAdapter {

    Context context;
    List<Package> data;
    private static LayoutInflater inflater = null;

    public PackageList(Context context, List<Package> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
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
        TextView title = (TextView) view.findViewById(R.id.name);
        title.setText(data.get(i).getName());

        TextView subtitle = (TextView) view.findViewById(R.id.text);
        subtitle.setText(data.get(i).getStatus());

        return view;
    }
}
