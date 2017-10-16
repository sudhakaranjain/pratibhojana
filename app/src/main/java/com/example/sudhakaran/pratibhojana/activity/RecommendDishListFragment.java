package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.adapter.RecommendDishes_adapter;

import java.util.ArrayList;

public class RecommendDishListFragment extends Fragment {

    Context context;
    String meal;
    ListView lv;
    ArrayList<String> list;
    ArrayList<String> diseases, temp;
    DBHelper dbh;
    fragmentlistener listener;

    public RecommendDishListFragment(Context context, String meal, fragmentlistener listener) {
        // Required empty public constructor
        this.context = context;
        this.meal = meal;
        this.listener = listener;
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
        View view = inflater.inflate(R.layout.fragment_recommend_dish_list, container, false);
        list = new ArrayList<>();
        list.clear();
        diseases = dbh.getdiseases();

        for(int i=0;i<diseases.size();i++)
        {

            temp = dbh.getrecommenddishes(meal,diseases.get(i));
            ArrayList<String> common = new ArrayList<>();
            common.clear();

            for(int j=0;j<temp.size();j++)
            {
                int k = 0;
                while (k < list.size()) {

                  if (temp.get(j).equals(list.get(k)))
                  {
                      common.add(list.get(k));
                      break;
                  }
                        k++;
               }
                if(list.isEmpty()) {
                    common = temp;
                    break;
                }
            }
            list = common;
        }

        if(list.isEmpty())
        {
            list = dbh.getrecommenddishes(meal);
        }

        lv = (ListView) view.findViewById(R.id.lv);
        RecommendDishes_adapter recommenddishes_adapter = new RecommendDishes_adapter(context,list,meal,listener);
        lv.setAdapter(recommenddishes_adapter);
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
