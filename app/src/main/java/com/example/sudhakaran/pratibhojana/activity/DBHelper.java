package com.example.sudhakaran.pratibhojana.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sudhakaran.pratibhojana.model.AlarmDishes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Sudhakaran on 15-Dec-15.
 */
public class DBHelper extends SQLiteOpenHelper {

    int i = 0;
    String DB_PATH = "";
    String s[] = new String[20];
    Context context;

    public DBHelper(Context context) {
        super(context, "pratibhojana.db", null, 1, null);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.context = context;
    }


    void createdb() {
        boolean db_exist = checkDB();
        if (!db_exist) {
            this.getWritableDatabase();
            this.close();
            try {
                dbcopy();
            } catch (IOException e) {
                //Do nothing
            }
        }

    }

    boolean checkDB() {
        File dbfile = new File(DB_PATH + "pratibhojana.db");
        return dbfile.exists();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("Create table family (id integer primary key, name text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dbcopy() throws IOException {
        OutputStream os = new FileOutputStream(DB_PATH + "pratibhojana.db");
        InputStream is = context.getAssets().open("pratibhojana.db");
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        is.close();
        os.close();
    }


    public void insertuserdata(String name, int age, String cuisine, float bmi, float bmr) {
        createdb();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", 1);
        values.put("user_name", name);
        values.put("user_age", age);
        values.put("user_bmi", bmi);
        values.put("bmi_classification", getbmitype(bmi));
        values.put("user_cuisine", cuisine);
        values.put("user_bmr", bmr);
        db.update("USER_DETAILS", values, "USER_ID=1", null);
        //db.execSQL("INSERT OR REPLACE INTO USER_DETAILS (USER_ID, USER_NAME, USER_AGE, USER_CUISINE, USER_BMI, BMI_CLASSIFICATION) VALUES(" + 1 + ",\"" + name + "\"," + age + "," + bmi + ",\"" + getbmitype(bmi) + "\")");

    }

    public Cursor getuserdetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user_details where user_id=1", null);
        return c;
    }

    public Cursor getingredients(String type) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select name from " + type, null);
        return c;
    }

    public Cursor getrecalldishes() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select name from dishes", null);
        return c;
    }

    public String getbmitype(float bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        String type = "";
        float value;


        for (int id = 8; id > 0; id--) {
            Cursor c = db.rawQuery("select start from bmi where id=" + id, null);
            c.moveToFirst();
            value = Float.parseFloat(c.getString(0));
            if (bmi > value) {
                Cursor d = db.rawQuery("select type from bmi where id=" + id, null);
                d.moveToFirst();
                type = d.getString(0);
                break;
            }

        }

        return type;

    }

    public void deletedata(int id) {
        createdb();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("family", "id = " + id, null);
    }

    public String[] display() {
        i = 0;
        createdb();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from family", null);
        c.moveToFirst();
        while (!c.isAfterLast() && i < 20) {
            s[i] = c.getString(0) + " " + c.getString(1);
            i++;
            c.moveToNext();
        }
        c.close();
        while (i < 20) {
            s[i] = "";
            i++;
        }
        return s;
    }

    public Cursor getnutrientvalues(String ingredienttype, String ingredient) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select calories, carbohydrates, fats, proteins, iron, calcium from " + ingredienttype + " where name= '" + ingredient + "'", null);

        return c;
    }

    public void insertuserrecall(String ingredient, String dish, Float calories, Float carbs, Float fats, Float proteins, Float iron, Float calcium) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT OR REPLACE INTO USER_DAY (INGREDIENTS, DISH, CALORIES, CARBOHYDRATES, FATS, PROTEINS, IRON, CALCIUM) VALUES(\""+ingredient+"\",\""+dish+"\","+calories+","+carbs+","+fats+","+proteins+","+iron+","+calcium+")");
    }

    public Cursor gettotalcalories() {
        SQLiteDatabase db = getWritableDatabase();
        float total = 0.0f;
        Cursor c = db.rawQuery("Select calories,carbohydrates,fats,proteins,iron,calcium from user_day", null);
        return c;

    }

    public void insertdisease(int id, int present, String level) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("PRESENT", present);
        values.put("LEVEL", level);
        db.update("DISEASE", values, "id=" + id, null);
    }

    public void insertallergy(int id, int present) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("PRESENT", present);
        db.update("ALLERGY", values, "id=" + id, null);
    }

    public ArrayList<String> getdiseases() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select name from disease where present=1", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;
    }

    public ArrayList<String> getallergies() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select name from allergy where present=1", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;
    }


    public ArrayList<String> getrecommenddishes(String meal, String disease) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        //Cursor c = db.rawQuery("Select name from recommend_dishes where (meal='" + meal + "') AND (disease='" + disease + "')", null);
        Cursor c = db.query("recommend_dishes", new String[]{"name"}, "meal=? and disease=?", new String[]{meal, disease}, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;

    }

    public ArrayList<String> getrecommenddishes(String meal) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("Select distinct name from recommend_dishes where meal=\"" + meal + "\"", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;

    }

    public void insertalarmdish(String dish, String meal) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", dish);
        db.update("alarm_dishes", values, "meal=\"" + meal + "\"", null);

    }

    public ArrayList<AlarmDishes> getalarmdishes() {
        ArrayList<AlarmDishes> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;
        c = db.rawQuery("Select name, meal from alarm_dishes", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (!c.getString(0).equals("") && c.getString(0) != null) {
                AlarmDishes ad = new AlarmDishes(c.getString(0), c.getString(1));
                list.add(ad);
            }
            c.moveToNext();
        }
        return list;
    }

    public void deletealarm(String meal)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "");
        db.update("alarm_dishes",values,"meal=\"" + meal + "\"", null);
    }

    public  String getuserdaydishes()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;
        String str="";
        c = db.rawQuery("Select distinct dish from user_day",null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            str = str + c.getString(0) + " ";
            c.moveToNext();
        }
        return str;
    }

}
