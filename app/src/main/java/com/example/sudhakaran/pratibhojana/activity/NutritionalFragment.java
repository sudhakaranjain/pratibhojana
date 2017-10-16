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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

import com.example.sudhakaran.pratibhojana.R;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class NutritionalFragment extends Fragment {

    Context context;
    DBHelper dbh;
    Cursor c,c2;
    TextView bmitype,totalcalories,totalcarbs,totalfats,totalproteins,totaliron,totalcalcium;

    public NutritionalFragment(Context context) {
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
        View view = inflater.inflate(R.layout.fragment_nutritional, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_yourprofile);

        bmitype = (TextView) view.findViewById(R.id.bmitype);
        totalcalories = (TextView) view.findViewById(R.id.totalcalories);

        dbh = new DBHelper(context);

        c = dbh.getuserdetails();
        c.moveToFirst();
        bmitype.setText("Your BMI: " + c.getString(5));

        c2 = dbh.gettotalcalories();
        totalcalories.setText("Total Calories consumed: "+gettotalvalues(c2, 0));

        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry((gettotalvalues(c2,1)/gettotalvalues(c2, 0))*100, 0));
        yvalues.add(new Entry((gettotalvalues(c2,2)/gettotalvalues(c2, 0))*100, 1));
        yvalues.add(new Entry((gettotalvalues(c2,3)/gettotalvalues(c2, 0))*100, 2));
        yvalues.add(new Entry((gettotalvalues(c2,4)/gettotalvalues(c2, 0))*100, 3));
        yvalues.add(new Entry((gettotalvalues(c2,5)/gettotalvalues(c2, 0))*100, 4));

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Carbohydrates");
        xVals.add("Fats");
        xVals.add("Proteins");
        xVals.add("Iron");
        xVals.add("Calcium");

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        pieChart.animateXY(800, 800);

        return view;
    }

    public Float gettotalvalues(Cursor c, int position)
    {
        Float total = 0.0f;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            total = total + Float.parseFloat(c.getString(position));
            c.moveToNext();
        }
        return total;
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
