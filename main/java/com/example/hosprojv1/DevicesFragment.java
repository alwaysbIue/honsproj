package com.example.hosprojv1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DevicesFragment extends Fragment implements View.OnClickListener{

    //String[][] deviceList = new String[8][3];
    String USER ;
    TableLayout yourdevices;
    TableLayout alldevices;
    String[] devices;
    TableRow.LayoutParams rowParam = new TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View devicesView =  inflater.inflate(R.layout.fragment_devices, container, false);

        // set on click listener for the account button in top corner
        final Button accBtn = devicesView.findViewById(R.id.acc_btn) ;
        accBtn.setOnClickListener(this);

        USER = getArguments().getString("user");
        accBtn.setText(USER);

        alldevices = devicesView.findViewById(R.id.alldevices);
        yourdevices = devicesView.findViewById(R.id.yourdevices);
        devices = getResources().getStringArray(R.array.device_list);

        //populateLayout();

        return devicesView ;
    }

    @Override
    public void onClick(View v) {
        // start account settings activity
        switch (v.getId()) {
            case R.id.acc_btn:
                openAccSettings();
                break;
        }
    }

    public void openAccSettings(){
            Intent intent = new Intent(getActivity(), AccountMenu.class);
            intent.putExtra("user", USER);
            startActivity(intent);
    }


//    code to dynamically fill devices tables
//
//    private void populateLayout(){
//        int columnCounter1 = 0;
//        int rowCounter1 = 0;
//        int layout2Counter1 = 0;
//        int layout2Counter2 = 0;
//
//        // add first row to table layout
//        TableRow row0 = new TableRow(getContext());
//        row0.setLayoutParams(rowParam);
//        row0.setTag("row" + rowCounter1);
//
//
//        for (int i = 0; i < 8; i++) {
//
//            String[] string = devices[i].split(":");
//            String device = string[0];
//            String name = string[1];
//            String owner = string[2];
//            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
//
//
//            if (owner == USER) {
//                //populate the YOUR DEVICES TABLE with it
//                TextView tv = new TextView(getContext());
//                tv.setText(name);
//                tv.setLayoutParams(rowParam);
//
//                switch (device) {
//                    case "bulb":
//                        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.bulb_large, 0, 0);
//                        break;
//                    case "nest":
//                        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.nest_large, 0, 0);
//                        break;
//                    case "tv":
//                        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.tv_large, 0, 0);
//                        break;
//                    case "hub":
//                        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.hub_large, 0, 0);
//                        break;
//                }
//
//
//
//                 // add 1 to counter for layout
//                if (columnCounter1 < 2) {
//                    columnCounter1++;
//                } else {
//                    rowCounter1++;
//                    columnCounter1 = 0;
//
//
//                    TableRow row = new TableRow(getContext());
//                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                    row.setTag("row" + i);
//                }
//            } else {
//                //populate the other devices table
//                //make drawable = (device)
//                // make text = (name)
//
//                // add 1 to counter for layout
//                if (layout2Counter1 < 2) {
//                    layout2Counter1++;
//                } else {
//                    layout2Counter2++;
//                    layout2Counter1 = 0;
//                }
//            }
//        }
//    }


    // check what the rules are for fragment lifecycle stuff - this might be all irrelevant or not needed c:
    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifecycle", "MainActivity onStart") ;
    }

    @Override
    public void onPause() {

        super.onPause();
        Log.d("lifecycle", "MainActivity onPause") ;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycle", "MainActivity onResume") ;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifecycle", "MainActivity onStop") ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle", "MainActivity onDestroy1") ;
    }
}

// snippets

// add drawable with the same id as device string value to textview 'tv_small'

// FOUND THIS AT https://stackoverflow.com/questions/33474546/android-get-resource-id-by-string
//getIdentifier(String name, String defType, String defPackage);
//int resId = getResources().getIdentifier("signal_" + mSignalStrength, "drawable", getPackageName());

// DOESN'T WORK >:(((
//tv_small.setCompoundDrawablesRelativeWithIntrinsicBounds(0, ("R.drawable." + device) , 0, 0);