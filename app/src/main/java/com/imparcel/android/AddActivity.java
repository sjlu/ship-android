package com.imparcel.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.imparcel.android.models.Package;

/**
 * Created by sjlu on 7/1/15.
 */
public class AddActivity extends AppCompatActivity {

    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_package);
        
        this.textInput = (EditText) findViewById(R.id.input);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_package, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                Package pkg = new Package(this.textInput.getText().toString());
                pkg.save();
                setResult(-1);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
