package com.example.sudhakaran.pratibhojana.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sudhakaran.pratibhojana.activity.DietFragment;
import com.example.sudhakaran.pratibhojana.activity.NutritionalFragment;
import com.example.sudhakaran.pratibhojana.activity.PersonalFragment;
import com.example.sudhakaran.pratibhojana.activity.fragmentlistener;


/**
 * Created by Sudhakaran on 07-Mar-16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment fragment;
    Context context;
    fragmentlistener listener;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Context context, fragmentlistener listerner) {
        super(fm);
        this.listener = listerner;
        this.mNumOfTabs = NumOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                 fragment = new PersonalFragment(listener,context);
                return fragment;
            case 1:
                fragment = new DietFragment(context);
                return fragment;
            case 2 :
                fragment = new NutritionalFragment(context);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}