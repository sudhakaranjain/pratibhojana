package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;

public class DietFragment extends Fragment {

    Context context;
    TextView dishlist;
    DBHelper dbh;
    String dishes="";
    public DietFragment(Context context) {
        this.context = context;
        dbh = new DBHelper(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        dishlist = (TextView) view.findViewById(R.id.dishlist);
        dishes = dbh.getuserdaydishes();
        dishlist.setText(dishes);
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
