package com.example.tomato.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tomato.model.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "pomodoroDatabase";
    private static final String ID ="eid";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context, NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create tables - add local tables here
        Log.i("Database","-------------------------------Creating Tables--------------------------------");
        //table events is for the new events
        //add an unlock id in events later
        db.execSQL("CREATE TABLE IF NOT EXISTS events( eid INTEGER PRIMARY KEY AUTOINCREMENT, eventName TEXT, timeMinutes INTEGER ,timeSecond INTEGER, unlockpw TEXT) ");
        //stats is to show the completed versions to check for achievements
        db.execSQL("CREATE TABLE IF NOT EXISTS stats(id INTEGER PRIMARY KEY AUTOINCREMENT, eventName TEXT, timeMinutes INTEGER ,timeSecond INTEGER, date TEXT ) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS user(uid INTEGER PRIMARY KEY, email TEXT, name TEXT, password TEXT) ");


    }

    @Override
    //why do I need versions???
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Dropping tables -- delete all the tables that you've created to clear/ create new db
        Log.i("Database","-------------------------------Dropping Tables--------------------------------");
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS stats");
        db.execSQL("DROP TABLE IF EXISTS user");
        //create tables
        onCreate(db);

    }

    //open the database duhh
    public void openDatabase(){
        db = this.getWritableDatabase();
             onCreate(db);
             //onUpgrade(db,1,2);
    }



    //Create Events specific functions

    public void insertEvent(Model evt){
        ContentValues cv = new ContentValues();
        cv.put("eventName", evt.getTask());
        cv.put("timeMinutes",evt.getTimeMinute());
        cv.put("timeSecond",evt.getTimeSec());
        long res = db.insertOrThrow("events",null, cv);
        if(res == -1){
            Log.i("Database", "FAILED TO INSERT FUNCTION BEGIN CRYING IN 3.. 2.. 1..");
        }else{
            Log.i("Database", "INSERT FUNCTION SUCCESSFUL");
        }


    }

    public void insertStats(Model evt){
        ContentValues cv = new ContentValues();
        cv.put("eventName", evt.getTask());
        cv.put("timeMinutes",evt.getTimeMinute());
        cv.put("timeSecond",evt.getTimeSec());
        cv.put("date",evt.getDate());
        long res = db.insertOrThrow("stats",null, cv);
        if(res == -1){
            Log.i("Database", "FAILED TO INSERT FUNCTION BEGIN CRYING IN 3.. 2.. 1..");
        }else{
            Log.i("Database", "INSERT FUNCTION SUCCESSFUL");
        }


    }

    //Selecting values
    @SuppressLint("Range")
    public List<Model> getAllEvents(){
        List<Model> eventList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query("events",null,null,null,null,null,null,null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        Model event = new Model();
                        event.setId(cur.getInt(cur.getColumnIndex("eid")));
                        event.setTask(cur.getString(cur.getColumnIndex("eventName")));
                        event.setTimeMinute(cur.getInt(cur.getColumnIndex("timeMinutes")));
                        event.setTimeSec(cur.getInt(cur.getColumnIndex("timeSecond")));
                        //Log.i("Database",cur.getString(cur.getColumnIndex("eventName")) );
                        eventList.add(event);


                    }while(cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }
        return eventList;

    }

    @SuppressLint("Range")
    public int getStats(boolean potions,boolean today, String date){
        int sum = 0;
        int min = 0;
        int sec = 0;
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String date = dtf.format(LocalDateTime.now());

        Cursor cur = null;
        db.beginTransaction();
        try{
            if(today ){
                cur = db.rawQuery("SELECT * FROM stats WHERE date=?",new String[] {date});
            }else{
                cur = db.query("stats",null,null,null,null,null,null,null);
            }

            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                      sum++;
                      min +=cur.getInt(cur.getColumnIndex("timeMinutes"));
                      sec +=cur.getInt(cur.getColumnIndex("timeSecond"));


                    }while(cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }
        min +=(int)(sec/60);
        if(potions){
            return sum;
        }else{
            return min;
        }

    }

    public void updateEvent(int id, String name, int min, int sec){
        //db.rawQuery("UPDATE events SET eventName = ?, timeMinutes =?, timeSecond =? WHERE eid =?",new String[] {name, String.valueOf(min), String.valueOf(sec), String.valueOf(id)});
       // db.rawQuery("UPDATE events SET eventName = hi, timeMinutes =1, timeSecond =1 WHERE eid =0",null);
        ContentValues cv = new ContentValues();
        cv.put("eventName", name);
        cv.put("timeMinutes", min);
        cv.put("timeSecond", sec);
        Log.i("Testing---------------------------------------------------",String.valueOf(id));
        String where = "eid ="+id;


        if( db.update("events",cv,"eid=?", new String[] {String.valueOf(id)})==0){
            Log.i("DB TESTINGGGGG","returned 0 narrrrr cant find nothing");
        }
    }


    public void deleteTask(int id){
        db.delete("events","eid=?", new String[] {String.valueOf(id)});

    }



}
