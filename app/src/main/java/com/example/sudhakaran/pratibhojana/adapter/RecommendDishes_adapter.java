package com.example.sudhakaran.pratibhojana.adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.activity.DBHelper;
import com.example.sudhakaran.pratibhojana.activity.fragmentlistener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Sudhakaran on 12-Mar-16.
 */
public class RecommendDishes_adapter extends BaseAdapter {

    Context context;
    ArrayList<String> list;
    LayoutInflater inflater;
    TextView dishname,alarm;
    String meal, username="";
    DBHelper dbh;
    fragmentlistener listener;

    String speech="";

    public RecommendDishes_adapter(Context context, ArrayList<String> list, String meal, fragmentlistener listener)
    {
        this.context = context;
        this.meal = meal;
        this.list = list;
        dbh = new DBHelper(context);
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.recommend_dishes_layout,null);
        dishname = (TextView) view.findViewById(R.id.dishname);
        alarm = (TextView) view.findViewById(R.id.alarm);
        dishname.setText(list.get(position));
        Cursor c = dbh.getuserdetails();
        c.moveToFirst();
        username = c.getString(1);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertalarm(list.get(position), meal);
                speech = username +", it's time to have "+list.get(position);
                listener.openTimePickerDialog(false,speech);
                //listener.setrecommend();

            }
        });

        return view;
    }

    public void insertalarm(String dish,String meal)
    {
        dbh.insertalarmdish(dish, meal);

    }


}
