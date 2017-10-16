package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sudhakaran.library.FloatingActionButton;
import com.example.sudhakaran.library.FloatingActionsMenu;
import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.adapter.RVcard_adapter;
import com.example.sudhakaran.pratibhojana.model.AlarmDishes;

import java.util.ArrayList;
import java.util.List;

public class RecommendationFragment extends Fragment {

    Context context;
    fragmentlistener listener;
    List<AlarmDishes> alarmDishesList = new ArrayList<>();
    DBHelper dbh;

    public RecommendationFragment(fragmentlistener listener, Context context) {
        this.context = context;
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
        final View view = inflater.inflate(R.layout.fragment_recommendation, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_recommendation);

        alarmDishesList.clear();
        alarmDishesList = dbh.getalarmdishes();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        final View shadowview  = view.findViewById(R.id.shadowView);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        RVcard_adapter rvcard_adapter = new RVcard_adapter(context, alarmDishesList, listener);
        rvcard_adapter.notifyDataSetChanged();
        rv.setAdapter(rvcard_adapter);

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) view.findViewById(R.id.multiple_actions);

        menuMultipleActions.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                shadowview.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                shadowview.setVisibility(View.GONE);
            }
        });

        FloatingActionButton breakfast = (FloatingActionButton) view.findViewById(R.id.breakfast);
        FloatingActionButton morningsnacks = (FloatingActionButton) view.findViewById(R.id.morningsnacks);
        FloatingActionButton lunch = (FloatingActionButton) view.findViewById(R.id.lunch);
        FloatingActionButton eveningsnacks = (FloatingActionButton) view.findViewById(R.id.eveningsnacks);
        FloatingActionButton dinner = (FloatingActionButton) view.findViewById(R.id.dinner);

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setrecommenddishes("Breakfast",1);
                menuMultipleActions.collapse();
            }
        });

        morningsnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setrecommenddishes("Morning Snacks",2);
                menuMultipleActions.collapse();
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setrecommenddishes("Lunch",3);
                menuMultipleActions.collapse();
            }
        });

        eveningsnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setrecommenddishes("Evening Snacks",4);
                menuMultipleActions.collapse();
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.setrecommenddishes("Dinner",5);
                menuMultipleActions.collapse();
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
        notifyAll();
    }

}
