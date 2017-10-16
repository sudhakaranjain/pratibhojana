package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.adapter.Ingredientslist_adapter;

import java.util.ArrayList;

public class IngredientslistFragment extends Fragment {

    Context context;
    String type,dish;
    ListView lv;
    DBHelper dbh;
    Cursor c;
    fragmentlistener listener;

    public IngredientslistFragment(Context context,String type,String dish,fragmentlistener listener) {
        this.context = context;
        this.type = type;
        this.listener = listener;
        this.dish=dish;
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbh = new DBHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredientslist, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_recall);

        ArrayList<String> ingredients = new ArrayList<String>();
        lv = (ListView) view.findViewById(R.id.lv);
        c = dbh.getingredients(type);
        if(c.moveToFirst()) {
            do {
                ingredients.add(c.getString(0));
                c.moveToNext();
            }while(!c.isAfterLast());
        }

/*
        BroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String ingredient = intent.getStringExtra("ingredient");
                listener.setsingleingredient(type,ingredient);
                context.unregisterReceiver(BroadcastReceiver);
            }
        };

        context.registerReceiver(BroadcastReceiver, new IntentFilter("startsingleingredient"));  */

        Ingredientslist_adapter adapter = new Ingredientslist_adapter(context,ingredients,listener,type,dish);
        lv.setAdapter(adapter);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
