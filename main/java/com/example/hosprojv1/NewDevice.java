package com.example.hosprojv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class NewDevice extends AppCompatActivity implements View.OnClickListener {

    String user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);

        //set pop up to right dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        // set on click listeners for account buttons
        final TextView alice = findViewById(R.id.aliceBtn);
        alice.setOnClickListener(this);
        final TextView paul = findViewById(R.id.paulBtn);
        paul.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aliceBtn:
                user = "alice";
                close();
                break;
            case R.id.paulBtn:
                user = "paul";
                close();
                break;
        }
    }

    public void close(){
        Intent userRtn = new Intent();
        userRtn.putExtra("user", user);
        setResult(Activity.RESULT_OK, userRtn);
        finish();
    }

}
