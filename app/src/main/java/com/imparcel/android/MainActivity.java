package com.imparcel.android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.imparcel.android.models.Package;

import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Package.deleteAll(Package.class);

        Package pkg1 = new Package("9374889949033167223216");
        pkg1.save();

        Package pkg2 = new Package("1Z88Y7Y20347012571");
        pkg2.save();

        pkg1.getTrackingDetails();

        List<Package> packagesList = Package.listAll(Package.class);

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new PackageList(this, packagesList));
    }
}
