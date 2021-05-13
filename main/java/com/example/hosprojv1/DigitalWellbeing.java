package com.example.hosprojv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DigitalWellbeing extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_wellbeing);

        final Button r1  = findViewById(R.id.resource1btn);
        final Button r2  = findViewById(R.id.resource2btn);
        final Button r3  = findViewById(R.id.resource3btn);
        r1.setOnClickListener((View.OnClickListener) this);
        r2.setOnClickListener((View.OnClickListener) this);
        r3.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        String URL ;

        switch (v.getId()) {
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifecycle", "AccountMenu onStart") ;
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("lifecycle", "AccountMenu onRestart") ;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycle", "AccountMenu onPause") ;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "AccountMenu onResume") ;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle", "AccountMenu onStop") ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "AccountMenu onDestroy1") ;
    }
}
