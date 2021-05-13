package com.example.hosprojv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements LogFragment.TextClicked{

    SharedPreferences prefs;
    String USER ;
    Bundle userBndl = new Bundle();
    Fragment selectedFragment = null;
    int NEWDEV = 1;
    String[] commands;
    String[] cmd;

    int ABUSEALERT = 7;
    int paulInd;
    int aliceInd;
    Boolean alicealert ;
    Boolean paulalert ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        // put user key ain bundle to send to fragments
        userBndl.putString("user", USER);
        commands = getResources().getStringArray(R.array.commands);

        init();
        checkalert();

        // set user bundle as fragment class argument
        selectedFragment = new HomeFragment();
        selectedFragment.setArguments(userBndl);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();

        // bottom nav bar on select listener
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    @Override
    public void sendText(String[] command){
        monitorLog(command);
    }

    public void newDevice(){
        //show new device pop up
        // need to have this intent return the USER string
        Intent newDevice = new Intent( MainActivity.this, NewDevice.class);
        startActivityForResult(newDevice, NEWDEV);
    }

    private void init(){
        String user = prefs.getString("user", null);
        alicealert = prefs.getBoolean("alicealert", false);
        paulalert = prefs.getBoolean("paulalert", false);

        if (user != null){
            USER = user ;
        }
        else{
           newDevice();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEWDEV){
            String user = data.getStringExtra("user");
            USER = user;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user", USER);
        editor.apply();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private void monitorLog(String[] cmd){

            String user = cmd[0];
            String indicator = cmd[4];

            if (indicator.equals("y")){
                switch (user){
                    case("paul"):
                        paulInd++;
                        break;
                    case("alice"):
                        aliceInd++;
                        break;
                }
            }

            if (paulInd > ABUSEALERT){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("alicealert", true);
                editor.apply();
                paulInd = 0 ;
            }
            if (aliceInd > ABUSEALERT){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("paulalert", true);
                editor.apply();
                aliceInd = 0 ;
            }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private void checkalert() {

        SharedPreferences.Editor editor = prefs.edit();
        if (alicealert){
            if (USER.equals("alice")) {
                //intent to start pop up
                Intent alertPopUp = new Intent( MainActivity.this, PopUp.class);
                startActivity(alertPopUp);
                editor.putBoolean("alicealert", false);
                editor.apply();
            }
        }
        if (paulalert){
            if (USER.equals("paul")) {
                //intent to start pop up
                Intent alertPopUp = new Intent( MainActivity.this, PopUp.class);
                startActivity(alertPopUp);
                editor.putBoolean("paulalert", false);
                editor.apply();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    //make a new fragment for whichever one was selected
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_devices:
                            selectedFragment = new DevicesFragment();
                            break;
                        case R.id.nav_log:
                            selectedFragment = new LogFragment();
                            // put command string array in bundle to send to fragments
                            userBndl.putStringArray("commands", commands);
                            break;
                    }

                    // put user key in bundle to send to fragments
                    userBndl.putString("user", USER);

                    // display the selected fragment as assigned above
                    selectedFragment.setArguments(userBndl);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();

                    return true;
                }
            };
}

/*// checks alert boolean to see if an abuse alert is needed
        if (alicealert){

        }*/