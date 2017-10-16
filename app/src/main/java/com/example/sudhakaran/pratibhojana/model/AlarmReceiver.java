package com.example.sudhakaran.pratibhojana.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sudhakaran.pratibhojana.activity.Alarm;
import com.example.sudhakaran.pratibhojana.activity.DBHelper;

public class AlarmReceiver extends BroadcastReceiver {

    String speech,meal;

    @Override
    public void onReceive(Context arg0, Intent arg1) {

        speech= arg1.getStringExtra("speech");
        meal = arg1.getStringExtra("meal");
        //Toast.makeText(arg0,dish, Toast.LENGTH_LONG).show();
        Intent i=new Intent(arg0, Alarm.class);
        i.putExtra("speech",speech);
        i.putExtra("meal",meal);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startActivity(i);
        }

}


