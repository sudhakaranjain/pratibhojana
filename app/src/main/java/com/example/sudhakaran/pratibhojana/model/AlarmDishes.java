package com.example.sudhakaran.pratibhojana.model;

/**
 * Created by Sudhakaran on 12-Mar-16.
 */
public class AlarmDishes {

    String dishname,meal;

    public AlarmDishes(String dishname, String meal)
    {
        this.meal = meal;
        this.dishname = dishname;
    }

    public String getMeal()
    {
        return meal;
    }

    public String getDishname()
    {
        return dishname;
    }

}
