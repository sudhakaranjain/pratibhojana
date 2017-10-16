package com.example.sudhakaran.pratibhojana.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.activity.fragmentlistener;

import java.util.ArrayList;

/**
 * Created by Sudhakaran on 19-Feb-16.
 */
public class Ingredientslist_adapter extends BaseAdapter {
    Context context;
    ArrayList<String> ingredients;
    LayoutInflater inflater;
    TextView ingredient;
    ImageView image;
    fragmentlistener listener;
    String type,dish;

    public Ingredientslist_adapter(Context context,ArrayList<String> ingredients,fragmentlistener listener,String type,String dish)
    {
        this.context = context;
        this.ingredients = ingredients;
        this.listener = listener;
        this.type = type;
        this.dish=dish;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.single_ingredient_row,null);

        image = (ImageView) view.findViewById(R.id.image);
        ingredient = (TextView) view.findViewById(R.id.ingredient);

        image.setImageResource(R.drawable.dish);
        ingredient.setText(ingredients.get(position));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setsingleingredient(type,ingredients.get(position),dish);

               /* Intent intent = new Intent("startsingleingredient");
                intent.putExtra("ingredient",ingredients.get(position));
                context.sendBroadcast(intent); */
            }
        });
        return view;
    }
}
