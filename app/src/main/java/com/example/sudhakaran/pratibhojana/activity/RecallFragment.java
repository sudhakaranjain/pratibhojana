package com.example.sudhakaran.pratibhojana.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.adapter.Custom_adapter;
import com.example.sudhakaran.pratibhojana.model.RecallDishes;

import java.util.ArrayList;
import java.util.Locale;

public class RecallFragment extends Fragment implements TextToSpeech.OnInitListener {

    Context context;
    Button submit;
    EditText search;
    ListView lv;
    RelativeLayout layout;

    TextToSpeech tts;
    Animation in;
    TranslateAnimation slide;
    AnimationSet set;
    BroadcastReceiver mBroadcastReceiver;
    fragmentlistener listener;
    DBHelper dbh;
    Cursor c;

    public RecallFragment(fragmentlistener listener,Context context) {
        // Required empty public constructor
        this.listener = listener;
        this.context = context;
        dbh = new DBHelper(context);
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = dbh.getrecalldishes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recall, container, false);

        ((MainActivity)getActivity()).setActionBarTitle(R.string.title_recall);

        tts = new TextToSpeech(context,this);
        Toast.makeText(context, "NOW IT'S TIME TO KNOW YOUR DIET HISTORY", Toast.LENGTH_LONG).show();

        ArrayList<RecallDishes> arraylist = new ArrayList <RecallDishes>();

        if(c.moveToFirst()) {
            do {
                RecallDishes rd = new RecallDishes(c.getString(0));
                arraylist.add(rd);
                c.moveToNext();
            }while(!c.isAfterLast());
        }

        submit = (Button) view.findViewById(R.id.submit);
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setVisibility(View.INVISIBLE);
        search = (EditText) view.findViewById(R.id.search);
        search.setVisibility(View.INVISIBLE);

        layout = (RelativeLayout) view.findViewById(R.id.relative_layout);

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1500);

        /*slide = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,Animation.RELATIVE_TO_PARENT,0.0f,Animation.RELATIVE_TO_PARENT,1.0f,Animation.RELATIVE_TO_PARENT,0.0f);
        slide.setDuration(1100);
        slide.setFillAfter(true);

        set = new AnimationSet(false);
        set.addAnimation(in);
        set.addAnimation(slide); */

        final Custom_adapter customAdapter = new Custom_adapter(context,arraylist,listener);
        lv.setAdapter(customAdapter);

        search.postDelayed(new Runnable() {
            public void run() {
                search.startAnimation(in);
                lv.startAnimation(in);
                search.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);
            }
        }, 1000);

        // Capture Text in EditText
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                customAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Saved!",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                tts.speak("Could you please tell us what you had yesterday", TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
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