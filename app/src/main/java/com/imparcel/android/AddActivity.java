package com.imparcel.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

    private void saveCurrentInput() {
        String input = this.textInput.getText().toString().trim().toUpperCase();
        if (input.equals("")) {
            this.textInput.setError("Tracking code is required");
            return;
        }

        Package pkg = new Package(input);
        pkg.save();
    }

    private void closeAndFinish() {
        setResult(RESULT_OK);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                saveCurrentInput();
                closeAndFinish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openScanner(View view) {
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String trackingCode = data.getStringExtra("contents");
            
            trackingCode = trackingCode.replace("42010003", "");

            textInput.setText(trackingCode);

            saveCurrentInput();
            closeAndFinish();

//            String format = data.getStringExtra("format");
        }
    }


}
