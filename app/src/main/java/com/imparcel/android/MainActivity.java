package com.imparcel.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.imparcel.android.models.Package;
import com.imparcel.android.adapters.PackageListAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        Package.deleteAll(Package.class);
//        Package pkg1 = new Package("9374889949033167223216");
//        pkg1.name = "Power Strips";
//        pkg1.status = "Delivered";
//        pkg1.save();
//
//        Package pkg2 = new Package("1Z88Y7Y20347012571");
//        pkg2.save();

        listview = (ListView) findViewById(R.id.listview);

        listview.setAdapter(new PackageListAdapter(this, this));
    }
}
