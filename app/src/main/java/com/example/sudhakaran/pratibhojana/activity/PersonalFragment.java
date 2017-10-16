package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;

import java.util.ArrayList;

public class PersonalFragment extends Fragment {

    Context context;
    TextView name,age,bmi,diseases,allergies,bmr,edit_personal,edit_diseases,edit_allergy;
    DBHelper dbh;
    Cursor c;
    String disease_data="";
    String allergy_data="";
    fragmentlistener listener;

    public PersonalFragment(fragmentlistener listener,Context context) {
        // Required empty public constructor
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       dbh = new DBHelper(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_yourprofile);

        ArrayList<String> d_list;
        ArrayList<String> a_list;

        disease_data="";
        allergy_data="";
        name = (TextView) view.findViewById(R.id.name);
        age = (TextView) view.findViewById(R.id.age);
        bmi = (TextView) view.findViewById(R.id.bmi);
        diseases = (TextView) view.findViewById(R.id.diseases);
        allergies = (TextView) view.findViewById(R.id.allergies);

        edit_personal = (TextView) view.findViewById(R.id.edit_personal);
        edit_diseases = (TextView) view.findViewById(R.id.edit_diseases);
        edit_allergy = (TextView) view.findViewById(R.id.edit_allergy);

        bmr = (TextView) view.findViewById(R.id.bmr);

        c = dbh.getuserdetails();
        c.moveToFirst();
        name.setText(c.getString(1));
        age.setText(c.getString(2));
        bmi.setText(c.getString(4));
        bmr.setText(c.getString(6));

        d_list = dbh.getdiseases();
        a_list = dbh.getallergies();

        edit_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setpersonaldetails();

            }
        });

        edit_diseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setdisease();

            }
        });

        edit_allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setallergy();

            }
        });

        for(int i =0;i<d_list.size();i++)
        disease_data = disease_data +" "+ d_list.get(i);
        for(int i =0;i<a_list.size();i++)
        allergy_data = allergy_data +" "+ a_list.get(i);
        diseases.setText(disease_data);
        allergies.setText(allergy_data);

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
