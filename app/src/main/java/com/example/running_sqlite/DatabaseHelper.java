package com.example.running_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.type.DateTime;

import java.time.DateTimeException;
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String RUNNING="running.db";
    public static final String RUNNER="runner_table";
    public static final String COL_1="ID";
    public static final String COL_2="FULL_NAME";
    public static final String COL_3="CITY";
    public static final String COL_4="EMAIL";
    public static final String COL_5="DATE_DE_NAISSANCE";

    public DatabaseHelper(Context context) {
        super(context, RUNNING, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + RUNNER + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FULL_NAME text,CITY text,EMAIL text,DATE_DE_NAISSANCE text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop TABLE if exists " + RUNNING);
     onCreate(db);
    }
     public boolean insertData(String FULL_NAME, String CITY, String EMAIL, String DATE_DE_NAISSANCE)
      {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,FULL_NAME);
        contentValues.put(COL_3,CITY);
        contentValues.put(COL_4,EMAIL);
        contentValues.put(COL_5, DATE_DE_NAISSANCE);
        long result = db.insert(RUNNER,null,contentValues);
        if(result == -1)
        return  false;
        else
           return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+RUNNER,null);
        return res;
    }
    public boolean updateData(String  ID,String FULL_NAME, String CITY, String EMAIL, String DATE_DE_NAISSANCE)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,FULL_NAME);
        contentValues.put(COL_3,CITY);
        contentValues.put(COL_4,EMAIL);
        contentValues.put(COL_5,DATE_DE_NAISSANCE);
        db.update(RUNNER,contentValues,"ID = ?",new String[] {ID});
        return true;
    }
    public Integer deleteData(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RUNNER,"ID = ?",new String[] {ID});
    }
}
