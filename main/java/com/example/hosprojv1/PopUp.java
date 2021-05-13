package com.example.hosprojv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class PopUp extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        //set pop up to right dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.9));

        final Button closeBtn = findViewById(R.id.close_btn);
        final Button showLater = findViewById(R.id.later);
        closeBtn.setOnClickListener(this);
        showLater.setOnClickListener(this);

        final Button r1  = findViewById(R.id.resource1btn);
        final Button r2  = findViewById(R.id.resource2btn);
        final Button r3  = findViewById(R.id.resource3btn);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) { //Handler function for processing multiple options via IDs

        String URL = "";

        switch (v.getId()) {
            case R.id.close_btn:
                closePop();
                break;
            case R.id.later:
                closePop();
                break;
            case R.id.resource1btn:
                URL = getResources().getString(R.string.resource1URL) ;
                openWebIntent(URL);
                break;
            case R.id.resource2btn:
                URL = getResources().getString(R.string.resource2URL) ;
                openWebIntent(URL);
                break;
            case R.id.resource3btn:
                URL = getResources().getString(R.string.resource3URL) ;
                openWebIntent(URL);
                break;
        }
    }

    private void openWebIntent(String url) {
        Intent webintent = new Intent(this, WebActivity.class);
        webintent.putExtra("url", url);
        startActivity(webintent);
    }

    public void closePop(){
        finish();
    }

}

