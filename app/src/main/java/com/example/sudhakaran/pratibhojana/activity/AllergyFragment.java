package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;

public class AllergyFragment extends Fragment {

    Context context;
    Button save;
    int present[];
    DBHelper dbh;
    boolean flag;
    CheckBox seafood,nuts,milk,acidity,not;
    ArithmeticException a;

    public AllergyFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_allergy, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_allergy);

        seafood = (CheckBox) view.findViewById(R.id.seafood);
        nuts = (CheckBox) view.findViewById(R.id.nuts);
        milk = (CheckBox) view.findViewById(R.id.milk);
        acidity = (CheckBox) view.findViewById(R.id.acidity);
        not = (CheckBox) view.findViewById(R.id.not);

        save = (Button) view.findViewById(R.id.save);

        dbh = new DBHelper(context);
        flag = false;
        a = new ArithmeticException();
        present = new int[4];

        for(int i=0;i<4;i++)
        {
            present[i]=0;
        }

        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(not.isChecked()) {

                    seafood.setChecked(false);
                    nuts.setChecked(false);
                    milk.setChecked(false);
                    acidity.setChecked(false);

                    seafood.setEnabled(false);
                    nuts.setEnabled(false);
                    milk.setEnabled(false);
                    acidity.setEnabled(false);
                }
                else
                {
                    seafood.setEnabled(true);
                    nuts.setEnabled(true);
                    milk.setEnabled(true);
                    acidity.setEnabled(true);
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (not.isChecked()) {
                        for (int i = 0; i < 4; i++) {
                            present[i] = 0;
                        }
                        flag = true;
                    }
                    else {
                        if (seafood.isChecked()) {
                            present[0] = 1;
                            flag = true;
                        }
                        if (nuts.isChecked()) {
                            present[1] = 1;
                            flag = true;
                        }
                        if (milk.isChecked()) {
                            present[2] = 1;
                            flag = true;
                        }
                        if (acidity.isChecked()) {
                            present[3] = 1;
                            flag = true;
                        }
                    }

                    if (flag) {
                        for (int i = 0; i < 4; i++) {
                            dbh.insertallergy(i + 1, present[i]);
                            present[i]=0;
                        }
                        Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                        throw a;
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }
                flag = false;
            }
        });

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
