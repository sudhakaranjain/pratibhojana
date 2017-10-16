package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;

public class IngredientstypeFragment extends Fragment {

    Context context;
    Button vegetables,fruits,cereals_grains,condiments_spices,diary_products,fats_sugars_oils,nuts,pulses_legumes,meat_seafood;
    fragmentlistener listener;
    String dish;

    public IngredientstypeFragment(fragmentlistener listener, Context context, String dish) {
        this.listener = listener;
        this.context = context;
        this.dish = dish;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredientstype, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_recall);

        vegetables = (Button) view.findViewById(R.id.vegetables);
        fruits = (Button) view.findViewById(R.id.fruits);
        cereals_grains = (Button) view.findViewById(R.id.cereals_grains);
        nuts = (Button) view.findViewById(R.id.nuts);
        diary_products = (Button) view.findViewById(R.id.diary_products);
        fats_sugars_oils = (Button) view.findViewById(R.id.fats_sugars_oils);
        condiments_spices = (Button) view.findViewById(R.id.condiments_spices);
        pulses_legumes = (Button) view.findViewById(R.id.pulses_legumes);
        meat_seafood = (Button) view.findViewById(R.id.meat_seafood);

        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("vegetables",dish);
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("fruits",dish);
            }
        });

        condiments_spices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("condiments_spices",dish);
            }
        });

        nuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("nuts",dish);
            }
        });

        cereals_grains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("cereals_grains",dish);
            }
        });

        diary_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("diary_products",dish);
            }
        });

        fats_sugars_oils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("fats_sugars_oils",dish);
            }
        });

        pulses_legumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("pulses_legumes",dish);
            }
        });

        meat_seafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setingredientlist("meat_seafood",dish);
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
