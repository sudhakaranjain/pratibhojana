package com.example.sudhakaran.pratibhojana.activity;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;

public class SingleIngredientFragment extends Fragment {

    Context context;
    Button save, back;
    int value;
    Cursor c;
    TextView ingredientname,calories,quantity,usercalories;
    Spinner number;
    String ingredient,ingredienttype,dish;
    Float calories_per_100,carbs_per_100,fats_per_100,proteins_per_100,iron_per_100,calcium_per_100;
    Float totalcalories,totalcarbs,totalfats,totalproteins,totaliron,totalcalcium;
    DBHelper dbh;
    ArithmeticException a;

    public SingleIngredientFragment(Context context,String ingredienttype,String ingredient,String dish) {
        // Required empty public constructor
        this.context = context;
        this.ingredienttype = ingredienttype;
        this.ingredient = ingredient;
        this.dish=dish;
        dbh = new DBHelper(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_ingredient, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_recall);

        a = new ArithmeticException();
        ingredientname = (TextView) view.findViewById(R.id.ingredientname);
        calories = (TextView) view.findViewById(R.id.calories);
        quantity = (TextView) view.findViewById(R.id.quantity);
        number = (Spinner) view.findViewById(R.id.number);
        usercalories = (TextView) view.findViewById(R.id.usercalories);
        save = (Button) view.findViewById(R.id.save);
        back = (Button) view.findViewById(R.id.back);

        c = dbh.getnutrientvalues(ingredienttype, ingredient);
        c.moveToFirst();

        try {
            calories_per_100 = Float.parseFloat(c.getString(0));
        } catch (Exception e) {
            calories_per_100 = 0.0f;
        }

        try {
            carbs_per_100 = Float.parseFloat(c.getString(1));
        } catch (Exception e) {
            carbs_per_100 = 0.0f;
        }

        try {
            fats_per_100 = Float.parseFloat(c.getString(2));
        } catch (Exception e) {
            fats_per_100 = 0.0f;
        }

        try {
            proteins_per_100 = Float.parseFloat(c.getString(3));
        } catch (Exception e) {
            proteins_per_100 = 0.0f;
        }

        try {
            iron_per_100 = Float.parseFloat(c.getString(4));
        } catch (Exception e) {
            iron_per_100 = 0.0f;
        }

        try {
            calcium_per_100 = Float.parseFloat(c.getString(5));
        } catch (Exception e) {
            calcium_per_100 = 0.0f;
        }

        ingredientname.setText(ingredient);
        calories.setText("Calories per 100g: "+calories_per_100.toString());

        String[] numberlist = {"Select quantity","1","2","3","4","5","6","7","8","9","10"};
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.spinner_item, numberlist);
        number.setAdapter(adapter);


        number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    value = Integer.parseInt(number.getSelectedItem().toString());
                    totalcalories = calories_per_100 * value;
                    usercalories.setText("Total calories: " + totalcalories.toString());
                } else {
                    value = 0;
                    usercalories.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (value == 0) {
                        throw a;
                    } else {
                        totalcalories = calories_per_100 * value;
                        totalcarbs = carbs_per_100 * value;
                        totalfats = fats_per_100 * value;
                        totalproteins = proteins_per_100 * value;
                        totaliron = iron_per_100 * value;
                        totalcalcium = calcium_per_100 * value;
                        dbh.insertuserrecall(ingredient, dish, totalcalories,totalcarbs,totalfats,totalproteins,totaliron,totalcalcium);
                        Toast.makeText(context, "Saved successfully!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Invalid Input!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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