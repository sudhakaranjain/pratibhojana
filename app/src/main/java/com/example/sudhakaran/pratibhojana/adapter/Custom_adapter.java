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
import com.example.sudhakaran.pratibhojana.model.RecallDishes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sudhakaran on 27-Dec-15.
 */
public class Custom_adapter extends BaseAdapter {

    public Context mcontext;
    public String[] values;
    LayoutInflater inflater;
    private List<com.example.sudhakaran.pratibhojana.model.RecallDishes> recalldisheslist = null;
    private ArrayList<com.example.sudhakaran.pratibhojana.model.RecallDishes> arraylist;
    fragmentlistener listener;


    public Custom_adapter(Context context, List<RecallDishes> recalldisheslist,fragmentlistener listener) {
        super();
        mcontext = context;
        this.recalldisheslist = recalldisheslist;
        inflater = LayoutInflater.from(mcontext);
        this.arraylist = new ArrayList<RecallDishes>();
        this.arraylist.addAll(recalldisheslist);
        this.listener = listener;
        }

    @Override
    public int getCount() {
            return recalldisheslist.size();
    }

    @Override
    public Object getItem(int position) {
        return recalldisheslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        TextView dish;
        ImageView image;
    }


    @Override
    public View getView(final int position,View view,ViewGroup parent)
    {
        //int img[]={R.drawable.iphone,  R.drawable.samsung, R.drawable.sony, R.drawable.karbonn};
       /* String img ="@drawable/" + values[position];
        int imageResource = mcontext.getResources().getIdentifier(img, null, mcontext.getPackageName());
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_layout,parent,false);
        TextView tv = (TextView)view.findViewById(R.id.tv);
        ImageView iv = (ImageView)view.findViewById(R.id.iv);
        tv.setText(values[position]);
        iv.setImageResource(imageResource); */


        //String img ="@drawable/" + recalldisheslist.get(position).getDish().toLowerCase();
        String img = "@drawable/dish";
        int imageResource = mcontext.getResources().getIdentifier(img, null, mcontext.getPackageName());
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_layout, null);
            // Locate the TextViews in listview_item.xml
            holder.dish = (TextView) view.findViewById(R.id.tv);
            holder.image = (ImageView)view.findViewById(R.id.iv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.dish.setText(recalldisheslist.get(position).getDish());
        holder.image.setImageResource(imageResource);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               listener.setcategory(recalldisheslist.get(position).getDish());

            }
        });

        return view;

    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        recalldisheslist.clear();
        if (charText.length() == 0) {
            recalldisheslist.addAll(arraylist);
        }
        else
        {
            for (RecallDishes rd : arraylist)
            {
                if (rd.getDish().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    recalldisheslist.add(rd);
                }
            }
        }
        notifyDataSetChanged();
    }


}
