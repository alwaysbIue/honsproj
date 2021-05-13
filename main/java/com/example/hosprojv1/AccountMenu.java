package com.example.hosprojv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AccountMenu extends AppCompatActivity implements View.OnClickListener {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_menu);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            user = extras.getString("user"); }

        final TextView wellbeing = findViewById(R.id.dig_well);
        wellbeing.setOnClickListener(this);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(user);

    }

    @Override
    public void onClick(View v) { //Handler function for processing multiple options via IDs
        switch (v.getId()) {
            case R.id.dig_well:
                openDigWell();
                break;
        }
    }

    public void populateLayout(){

        //get USER from interface
        //set user name to USER string
        //set user pfp to USER pfp

    }

    public void openDigWell(){
        Intent digWellIntent = new Intent( AccountMenu.this, DigitalWellbeing.class);
        startActivity(digWellIntent);
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
