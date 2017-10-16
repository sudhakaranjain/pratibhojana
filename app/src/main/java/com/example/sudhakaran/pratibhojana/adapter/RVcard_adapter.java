package com.example.sudhakaran.pratibhojana.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.activity.Alarm;
import com.example.sudhakaran.pratibhojana.activity.DBHelper;
import com.example.sudhakaran.pratibhojana.activity.fragmentlistener;
import com.example.sudhakaran.pratibhojana.model.AlarmDishes;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sudhakaran on 12-Mar-16.
 */
public class RVcard_adapter extends RecyclerView.Adapter<RVcard_adapter.ViewHolder> {

    Context context;
    private LayoutInflater inflater;
    List<AlarmDishes> alarmDishesList = Collections.emptyList();
    DBHelper dbh;
    String meal = "";
    String speech = "";
    String username = "";
    int RQS_code = 0;
    fragmentlistener listener;

    public RVcard_adapter(Context context, List<AlarmDishes> alarmDishesList, fragmentlistener listener)
    {
        this.listener = listener;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.alarmDishesList = alarmDishesList;
        dbh = new DBHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_layout, parent, false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final AlarmDishes current = alarmDishesList.get(position);
        holder.meal.setText(current.getMeal());
        holder.dish.setText(current.getDishname());
        meal = current.getMeal();
        Cursor c = dbh.getuserdetails();
        c.moveToFirst();
        username = c.getString(1);
        speech = username +", it's time to have "+current.getDishname();

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.deletealarm(current.getMeal());
                listener.cancelAlarm(current.getMeal(),speech);
                alarmDishesList = dbh.getalarmdishes();
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return alarmDishesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView meal;
        TextView dish;
        ImageView cancel;
        public ViewHolder(View itemView) {
            super(itemView);
            meal = (TextView) itemView.findViewById(R.id.meal);
            dish = (TextView) itemView.findViewById(R.id.dish);
            cancel = (ImageView) itemView.findViewById(R.id.cancel);
        }
    }



}
