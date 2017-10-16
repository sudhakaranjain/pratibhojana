package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudhakaran.pratibhojana.R;


public class IntroFragment extends Fragment {
    Context context;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,click;
    ImageView arrow;
    Handler handler;
    Animation in1, in2,in3,in4;

    public IntroFragment(Context context) {
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
        View view =inflater.inflate(R.layout.fragment_intro, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(R.string.app_name);

 /*       in1 = new AlphaAnimation(0.0f,1.0f);
        in2 = new AlphaAnimation(0.0f,1.0f);
        in3 = new AlphaAnimation(0.0f,1.0f);
        in4 = new AlphaAnimation(0.0f,1.0f); */


        arrow = (ImageView) view.findViewById(R.id.arrow);
        click = (TextView) view.findViewById(R.id.click);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv6 = (TextView) view.findViewById(R.id.tv6);
        tv7 = (TextView) view.findViewById(R.id.tv7);
        tv8 = (TextView) view.findViewById(R.id.tv8);
        tv9 = (TextView) view.findViewById(R.id.tv9);

/*      arrow.setVisibility(View.INVISIBLE);
        click.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        tv7.setVisibility(View.INVISIBLE);
        tv8.setVisibility(View.INVISIBLE);
        tv9.setVisibility(View.INVISIBLE);

        in1.setDuration(1500);
        in2.setDuration(1500);
        in3.setDuration(1500);
        in4.setDuration(1500);

        tv1.startAnimation(in1);
        tv2.startAnimation(in1);
        tv3.startAnimation(in1);
        tv1.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        tv3.setVisibility(View.VISIBLE);

        in1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                tv4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv4.startAnimation(in2);
                        tv5.startAnimation(in2);
                        tv6.startAnimation(in2);
                        tv7.startAnimation(in2);
                        tv4.setVisibility(View.VISIBLE);
                        tv5.setVisibility(View.VISIBLE);
                        tv6.setVisibility(View.VISIBLE);
                        tv7.setVisibility(View.VISIBLE);

                    }
                },8500);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                tv8.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv8.setAnimation(in3);
                        tv8.setVisibility(View.VISIBLE);
                    }
                },8500);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                tv9.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv9.setAnimation(in4);
                        tv9.setVisibility(View.VISIBLE);

                    }
                },3000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        in4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                arrow.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arrow.setVisibility(View.VISIBLE);
                        click.setVisibility(View.VISIBLE);
                    }
                },3800);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

       /* handler = new Handler();
        showDelayed(tv1,0);
        showDelayed(tv2,3000);
        showDelayed(tv3,6000);
        showDelayed(tv4,9000);
        showDelayed(tv5,12000);
        showDelayed(tv6,15000);
        showDelayed(tv7,18000);
        showDelayed(tv8,21000);
        showDelayed(tv9,24000);
        showDelayed(arrow,25000);
        */



        return view;
    }

   /* public void showDelayed(final View v, int delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               v.setVisibility(View.VISIBLE);
            }
        }, delay);
    } */


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
