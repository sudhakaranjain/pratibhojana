package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;

public class PersonalDetailsFragment extends Fragment {

    Button save;
    TextView displaybmi, type;
    EditText weight, height, name, age1;
    RadioGroup rg1;
    float w,h,bmi,bmr;
    int age;
    int radio_id;
    String fname="",cuisine="";
    ArithmeticException a;
    DBHelper dbh ;
    Context context;

    public PersonalDetailsFragment(Context context) {
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
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_personaldetails);

        w=0.0f;
        h=0.0f;
        bmi=0.0f;
        bmr=0.0f;
        displaybmi = (TextView) view.findViewById(R.id.displaybmi);
        displaybmi.setVisibility(View.INVISIBLE);
        rg1 = (RadioGroup) view.findViewById(R.id.rg1);
        dbh = new DBHelper(context);
        weight = (EditText) view.findViewById(R.id.weight);
        weight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        height = (EditText) view.findViewById(R.id.height);
        height.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        save = (Button) view.findViewById(R.id.save);
        age1 = (EditText) view.findViewById(R.id.age);
        age1.setInputType(InputType.TYPE_CLASS_NUMBER);
        name = (EditText) view.findViewById(R.id.name);
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        type = (TextView) view.findViewById(R.id.type);
        a = new ArithmeticException();

        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                type.setText("");

                try
                {
                    w = Float.valueOf(weight.getText().toString());
                    h = Float.valueOf(height.getText().toString());
                    bmi = w/(h*h);
                }
                catch (Exception e)
                {
                    bmi= 0.0f;
                }

                if(bmi<100 && bmi>0) {
                    displaybmi.setText("Your BMI is: " + bmi);
                    displaybmi.setVisibility(View.VISIBLE);
                }
                else
                    displaybmi.setVisibility(View.INVISIBLE);

               /* if(w==0.0f || h==0.0f) {
                    displaybmi.setVisibility(View.INVISIBLE);
                }
                else
                    displaybmi.setVisibility(View.VISIBLE); */
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                type.setText("");

                try {
                    w = Float.valueOf(weight.getText().toString());
                    h = Float.valueOf(height.getText().toString());
                    bmi = w / (h * h);
                } catch (Exception e) {
                    bmi = 0.0f;
                }

                if (bmi < 100 && bmi > 0) {
                    displaybmi.setText("Your BMI is: " + bmi);
                    displaybmi.setVisibility(View.VISIBLE);
                } else
                    displaybmi.setVisibility(View.INVISIBLE);

                /*if(w==0.0f || h==0.0f) {
                    displaybmi.setVisibility(View.INVISIBLE);
                }
                else
                    displaybmi.setVisibility(View.VISIBLE); */
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    fname = name.getText().toString();
                    age = Integer.parseInt(age1.getText().toString());
                    radio_id = rg1.getCheckedRadioButtonId();
                    w = Float.valueOf(weight.getText().toString());
                    h = Float.valueOf(height.getText().toString());
                    bmr = (float) ((10*w) + (6.25*h*100) - (5*age) - 161);
                    if(radio_id!=-1) {
                        if (bmi < 100 && bmi > 0) {
                            if (age >= 40 && age <= 50) {
                                if(radio_id==R.id.veg)
                                    cuisine="veg";
                                else
                                cuisine="nonveg";
                                dbh.insertuserdata(fname, age, cuisine, bmi,bmr);
                                type.setText(dbh.getbmitype(bmi));
                                Toast.makeText(context, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                            } else
                                throw a;
                        } else {
                            type.setText("Invalid BMI");
                            throw a;
                        }
                    } else throw a;

                } catch (Exception e) {
                    Toast.makeText(context, "Invalid input, check again!", Toast.LENGTH_SHORT).show();
                }


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
