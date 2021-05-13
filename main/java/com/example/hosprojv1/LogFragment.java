package com.example.hosprojv1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogFragment extends Fragment implements View.OnClickListener {

    TextClicked mCallback;
    String USER ;
    String[] commands;
    ViewGroup.LayoutParams layoutparams ;
    LinearLayout loglayout;
    int counter = 30;
    int paulInd;
    int aliceInd;

    public interface TextClicked {
        public void sendText(String[] command);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View logview =  inflater.inflate(R.layout.fragment_log, container, false);

        USER = getArguments().getString("user");
        //Toast.makeText(getActivity(), USER, Toast.LENGTH_LONG).show();

        commands = getArguments().getStringArray("commands");
        layoutparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        loglayout = logview.findViewById(R.id.log);
        fillLog();

        final Button tstbtn = logview.findViewById(R.id.test) ;
        tstbtn.setOnClickListener((View.OnClickListener) this);

        return logview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (TextClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }

    // for test button to add to log //////////////////

    @Override
    public void onClick(View v) {
        // start account settings activity
        switch (v.getId()) {
            case R.id.test:
                addToLog();
                break;
        }
    }

    private void addToLog() {
        String[] cmd = commands[counter].split(":");
        addTextView(cmd);
        counter++;
        if (counter == 50){
            counter = 0;
        }
    }

    private void fillLog() {
        for (int i = 0; i < 30; i++) {
            String[] cmd = commands[i].split(":");
            addTextView(cmd);
        }
    }

    private void addTextView(String[] cmd) {
        mCallback.sendText(cmd);
        String user = cmd[0];
        String name = cmd[1];
        String device = cmd[2];
        String state = cmd[3];
        String indicator = cmd[4];
        String log = user + " set " + name + " to " + state ;

        //monitorLog(user, indicator);

        TextView tv = new TextView(getContext());
        tv.setText(log);
        switch (device) {
            case "bulb":
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.bulb_large, 0, 0, 0);
                break;
            case "nest":
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.nest_large, 0, 0, 0);
                break;
            case "tv":
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.tv_large, 0, 0, 0);
                break;
            case "hub":
                tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.hub_large, 0, 0, 0);
                break;
        }
        tv.setTextSize(20);
        tv.setPadding(10, 15, 10, 15);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setLayoutParams(layoutparams);
        loglayout.addView(tv, 0);
    }

}
