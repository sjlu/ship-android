package com.imparcel.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import com.imparcel.android.models.Package;
import com.imparcel.android.adapters.PackageListAdapter;

import java.util.List;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {

    ListView listview;
    PackageListAdapter packageListAdapter;

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

//        Package.deleteAll(Package.class);
//        Package pkg1 = new Package("9374889949033167223216");
//        pkg1.name = "Power Strips";
//        pkg1.status = "Delivered";
//        pkg1.save();
//
//        Package pkg2 = new Package("1Z88Y7Y20347012571");
//        pkg2.save();

        packageListAdapter = new PackageListAdapter(this, this);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(packageListAdapter);
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

}
