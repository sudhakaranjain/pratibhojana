package com.example.sudhakaran.pratibhojana.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.model.AlarmReceiver;

import java.util.Locale;

public class Alarm extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    String speech="";
    String meal="";
    static int CRQS_code = 0;
    Thread t;
    fragmentlistener  listener;
    volatile int j=1;
    ImageButton ib;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        dbh = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        speech = bundle.getString("speech");
        meal = bundle.getString("meal");

        ib = (ImageButton) findViewById(R.id.ib);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    j=0;
                dbh.deletealarm(meal);

                if(meal.equals("Breakfast"))
                    CRQS_code = 1;
                else if(meal.equals("Morning Snacks"))
                    CRQS_code = 2;
                else if(meal.equals("Lunch"))
                    CRQS_code = 3;
                else if(meal.equals("Evening Snacks"))
                    CRQS_code = 4;
                else if(meal.equals("Dinner"))
                    CRQS_code = 5;

                Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
                intent.putExtra("speech", speech);
                intent.putExtra("meal", meal);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), CRQS_code, intent,0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                finish();
                }
        });

         tts = new TextToSpeech(this, this);
         Toast.makeText(getBaseContext(), speech, Toast.LENGTH_LONG).show();

    }

    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            else {
                   speakOut();
                }


        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

        void speakOut() {

                t = new Thread() {
                @Override
                public void run() {

                    try {
                        while(j!=0) {
                            tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
                            Thread.sleep(4000);
                        }

                    } catch (InterruptedException e) {
                        //finish();
                    }

                }


            };
            t.start();
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
