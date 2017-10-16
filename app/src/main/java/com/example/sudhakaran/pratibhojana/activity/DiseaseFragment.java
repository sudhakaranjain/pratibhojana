package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;

public class DiseaseFragment extends Fragment {

    Button save;
    CheckBox anemia,diabetes,thyroid,cholesterol,bp,osteoarthritis,not;
    Spinner s_anemia,s_diabetes, s_thyroid,s_cholesterol,s_bp;
    DBHelper dbh;
    Context context;
    boolean flag;
    ArithmeticException a;
    String level[];
    int present[];

    public DiseaseFragment(Context context) {
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
        View view = inflater.inflate(R.layout.fragment_disease, container, false);

        dbh = new DBHelper(context);
        a= new ArithmeticException();
        level = new String[6];
        present = new int[6];
        flag = false;
        for(int i=0;i<6;i++) {
            present[i] = 0;
            level[i]="";
        }

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_disease);

        save = (Button) view.findViewById(R.id.save);
        anemia = (CheckBox) view.findViewById(R.id.anemia);
        diabetes= (CheckBox) view.findViewById(R.id.diabetes);
        thyroid = (CheckBox) view.findViewById(R.id.thyroid);
        bp = (CheckBox) view.findViewById(R.id.bp);
        cholesterol = (CheckBox) view.findViewById(R.id.cholesterol);
        osteoarthritis = (CheckBox) view.findViewById(R.id.osteoarthritis);
        not = (CheckBox) view.findViewById(R.id.not);

        s_anemia = (Spinner) view.findViewById(R.id.s_anemia);
        s_diabetes = (Spinner) view.findViewById(R.id.s_diabetes);
        s_thyroid = (Spinner) view.findViewById(R.id.s_thyroid);
        s_cholesterol = (Spinner) view.findViewById(R.id.s_cholesterol);
        s_bp = (Spinner) view.findViewById(R.id.s_bp);

        s_anemia.setVisibility(View.INVISIBLE);
        s_diabetes.setVisibility(View.INVISIBLE);
        s_thyroid.setVisibility(View.INVISIBLE);
        s_cholesterol.setVisibility(View.INVISIBLE);
        s_bp.setVisibility(View.INVISIBLE);

        String[] categories_anemia = {"Choose your level..","Over (12-15.5g/dL)","Normal (8-12g/dL)","Severe (Below 8g/dL)"};
        String[] categories_diabetes = {"Choose your level..","Over","Normal","Under"};
        String[] categories_thyroid = {"Choose your level..","Hypothyroidism","Hyperthyroidism"};
        String[] categories_cholesterol = {"Choose your level..","Normal (Below 200mg/mL)","Moderate (200-239mg/mL)","High (Above 240mg/mL)"};
        String[] categories_bp = {"Choose your level..","Low (Below 90/60mmHg)","Normal (90/60-140/90mmHg)","High (Above 140/90mmHg)s"};

        ArrayAdapter adapter_anemia = new ArrayAdapter(context, R.layout.spinner_item, categories_anemia);
        ArrayAdapter adapter_diabetes = new ArrayAdapter(context, R.layout.spinner_item, categories_diabetes);
        ArrayAdapter adapter_thyroid = new ArrayAdapter(context, R.layout.spinner_item, categories_thyroid);
        ArrayAdapter adapter_cholesterol = new ArrayAdapter(context, R.layout.spinner_item, categories_cholesterol);
        ArrayAdapter adapter_bp = new ArrayAdapter(context, R.layout.spinner_item, categories_bp);


        adapter_anemia.setDropDownViewResource(R.layout.spinner_item);
        adapter_diabetes.setDropDownViewResource(R.layout.spinner_item);
        adapter_thyroid.setDropDownViewResource(R.layout.spinner_item);
        adapter_cholesterol.setDropDownViewResource(R.layout.spinner_item);
        adapter_bp.setDropDownViewResource(R.layout.spinner_item);

        s_anemia.setAdapter(adapter_anemia);
        s_diabetes.setAdapter(adapter_diabetes);
        s_thyroid.setAdapter(adapter_thyroid);
        s_cholesterol.setAdapter(adapter_cholesterol);
        s_bp.setAdapter(adapter_bp);


        anemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    s_anemia.setVisibility(View.VISIBLE);
                else
                    s_anemia.setVisibility(View.INVISIBLE);
            }


        });


        diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked())
                    s_diabetes.setVisibility(View.VISIBLE);

                else
                    s_diabetes.setVisibility(View.INVISIBLE);
            }



        });

        thyroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    s_thyroid.setVisibility(View.VISIBLE);

                else
                    s_thyroid.setVisibility(View.INVISIBLE);
            }


        });

        cholesterol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    s_cholesterol.setVisibility(View.VISIBLE);

                else
                    s_cholesterol.setVisibility(View.INVISIBLE);
            }


        });


        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    s_bp.setVisibility(View.VISIBLE);

                else
                    s_bp.setVisibility(View.INVISIBLE);
            }


        });


        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                {
                    anemia.setChecked(false);
                    diabetes.setChecked(false);
                    thyroid.setChecked(false);
                    bp.setChecked(false);
                    cholesterol.setChecked(false);
                    osteoarthritis.setChecked(false);

                    anemia.setEnabled(false);
                    diabetes.setEnabled(false);
                    thyroid.setEnabled(false);
                    bp.setEnabled(false);
                    cholesterol.setEnabled(false);
                    osteoarthritis.setEnabled(false);

                    s_anemia.setVisibility(View.INVISIBLE);
                    s_diabetes.setVisibility(View.INVISIBLE);
                    s_thyroid.setVisibility(View.INVISIBLE);
                    s_cholesterol.setVisibility(View.INVISIBLE);
                    s_bp.setVisibility(View.INVISIBLE);

                }

                else
                {
                    anemia.setEnabled(true);
                    diabetes.setEnabled(true);
                    thyroid.setEnabled(true);
                    bp.setEnabled(true);
                    cholesterol.setEnabled(true);
                    osteoarthritis.setEnabled(true);
                }

            }
        });


         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                try {

                     if (not.isChecked()) {

                         for (int i = 0; i < 6; i++) {
                             level[i] = "";
                             present[i] = 0;
                         }
                         flag = true;
                     }
                     else {
                         if (anemia.isChecked()) {
                             level[0] = s_anemia.getSelectedItem().toString();
                             if (level[0] == "Choose your level..")
                                 throw a;

                             present[0] = 1;
                             flag = true;


                         }
                         if (diabetes.isChecked()) {
                             level[1] = s_diabetes.getSelectedItem().toString();
                             if (level[1] == "Choose your level..")
                                 throw a;
                              present[1] = 1;
                              flag = true;
                         }
                         if (thyroid.isChecked()) {
                             level[2] = s_thyroid.getSelectedItem().toString();
                             if (level[2] == "Choose your level..")
                                 throw a;
                             present[2] = 1;
                             flag = true;
                         }
                         if (cholesterol.isChecked()) {
                             level[3] = s_cholesterol.getSelectedItem().toString();
                             if (level[3] == "Choose your level..")
                                 throw a;
                             present[3] = 1;
                             flag = true;
                         }
                         if (bp.isChecked()) {
                             level[4] = s_bp.getSelectedItem().toString();
                             if (level[4] == "Choose your level..")
                                 throw a;
                             present[4] = 1;
                             flag = true;
                         }
                         if (osteoarthritis.isChecked()) {
                             level[5] = "";
                             present[5] = 1;
                             flag = true;
                         }

                     }

                        if(flag) {
                            for (int i = 0; i < 6; i++) {
                                dbh.insertdisease(i + 1, present[i], level[i]);
                                present[i] = 0;
                                level[i] = "";
                            }

                            Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                            throw a;

                 }
                 catch (Exception e) {
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
