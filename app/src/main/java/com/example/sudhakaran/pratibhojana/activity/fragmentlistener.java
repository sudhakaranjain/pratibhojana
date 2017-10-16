package com.example.sudhakaran.pratibhojana.activity;

import java.util.Calendar;

/**
 * Created by Sudhakaran on 19-Feb-16.
 */

public interface fragmentlistener {
    public void setingredientlist(String type,String dish);
    public void setcategory(String dish);
    public void setsingleingredient(String type,String ingredient,String dish);
    public void setrecommenddishes(String meal, int RQS_code);
    public void setrecommend();
    public void setdisease();
    public void setpersonaldetails();
    public void setallergy();
    public void setAlarm(Calendar targetCal,String speech);
    public void cancelAlarm(String meal, String speech);
    public void openTimePickerDialog(boolean is24r, String speech);
    public void onbackpressed();

}