package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.adapter.PagerAdapter;

public class TabLayoutFragment extends Fragment {

    Context context;
    TabLayout tabLayout;
    fragmentlistener listener;

    public TabLayoutFragment(fragmentlistener listener, Context context) {
        // Required empty public constructor
        this.listener = listener;
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
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_yourprofile);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Diet"));
        tabLayout.addTab(tabLayout.newTab().setText("Nutritional"));

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount(), context, listener));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    listener.onbackpressed();
                    return true;
                }
                return false;
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
