package com.example.sudhakaran.pratibhojana.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sudhakaran.pratibhojana.R;
import com.example.sudhakaran.pratibhojana.model.AlarmReceiver;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,fragmentlistener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    static int RQS_code = 0;
    static int CRQS_code = 0;
    DBHelper dbh;
    Cursor userdetails;
    String userday_dishes;
    Fragment fragment=null;
    String title,speech;
    FragmentManager fragmentManager = getSupportFragmentManager();
    PersonalDetailsFragment personalDetailsFragment;
    DiseaseFragment diseaseFragment;
    AllergyFragment allergyFragment;
    RecallFragment recallFragment;
    RecommendationFragment recommendationFragment;
    IntroFragment introFragment;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        personalDetailsFragment = new PersonalDetailsFragment(getBaseContext());
        diseaseFragment = new DiseaseFragment(getBaseContext());
        allergyFragment  = new AllergyFragment(getBaseContext());
        recallFragment = new RecallFragment(this,getBaseContext());
        recommendationFragment = new RecommendationFragment(this,getBaseContext());
        introFragment = new IntroFragment(getBaseContext());

        dbh = new DBHelper(this);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if(id == R.id.action_search){
           // Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        fragment = null;
        title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = personalDetailsFragment;
                title = getString(R.string.title_personaldetails);
                break;
            case 1:
                fragment = diseaseFragment;
                title = getString(R.string.title_disease);
                break;
            case 2:
                fragment = allergyFragment;
                title = getString(R.string.title_allergy);
                break;
            case 3:
                fragment = recallFragment;
                title = getString(R.string.title_recall);
                break;
            case 4:
                fragment = new TabLayoutFragment(this, getBaseContext());
                title = getString(R.string.title_yourprofile);
                break;
            case 5:
                userdetails = dbh.getuserdetails();
                userdetails.moveToFirst();

                userday_dishes = dbh.getuserdaydishes();
                if(userdetails.getString(0) != " " && userday_dishes != "") {
                    fragment = new RecommendationFragment(this, getBaseContext());
                    title = getString(R.string.title_recommendation);
                }
                else
                    Toast.makeText(this, "Please fill your profile!", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                fragment = introFragment;
                title = getString(R.string.app_name);
                break;

            default:
                break;
        }

        if (fragment != null) {
            setView();
        }
    }

    @Override
    public void setingredientlist(String type,String dish) {
        fragment = new IngredientslistFragment(getBaseContext(),type,dish,this);
        title = getString(R.string.title_recall);
        setView();
    }

    @Override
    public void setcategory(String dish) {
        fragment = new IngredientstypeFragment(this, getBaseContext(),dish);
        title = getString(R.string.title_recall);
        setView();
    }

    @Override
    public void setsingleingredient(String type,String ingredient,String dish) {
        fragment = new SingleIngredientFragment(getBaseContext(),type,ingredient,dish);
        title = getString(R.string.title_recall);
        setView();
    }

    @Override
    public void setrecommenddishes(String meal, int RQS_code) {
        fragment = new RecommendDishListFragment(getBaseContext(),meal,this);
        this.RQS_code = RQS_code;
        setView();
    }

    @Override
    public  void setrecommend() {
        //fragment = new RecommendationFragment(this, getBaseContext());
        //setView();
        displayView(5);

    }

    @Override
    public void setpersonaldetails() {
        displayView(0);
    }

    @Override
    public void setdisease() {
        displayView(1);
    }

    @Override
    public void setallergy() {
        displayView(2);
    }

    public void openTimePickerDialog(boolean is24r, String speech) {
        Calendar calendar = Calendar.getInstance();
        this.speech = speech;
        timePickerDialog = new TimePickerDialog(
                this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();

    }

    @Override
    public void onbackpressed() {
        finish();
        super.onBackPressed();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                //Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet, speech);
        }
    };


    @Override
    public void setAlarm(Calendar targetCal, String speech) {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        String meal="";
        if(RQS_code==1)
        meal="Breakfast";
        else if(RQS_code == 2)
        meal = "Morning Snacks";
        else if(RQS_code == 3)
            meal = "Lunch";
        else if(RQS_code == 4)
            meal = "Evening Snacks";
        else if(RQS_code == 5)
            meal = "Dinner";
        intent.putExtra("speech", speech);
        intent.putExtra("meal", meal);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_code, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this,"Alarm set!",Toast.LENGTH_SHORT).show();
        setrecommend();
    }

    public void cancelAlarm(String meal, String speech){

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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), CRQS_code, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Alarm Cancelled!",Toast.LENGTH_SHORT).show();
    }

    public void setView(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.animation,R.anim.animation2);
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void setActionBarTitle(int title)
    {
        getSupportActionBar().setTitle(title);

    }

}