package com.example.myactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "classUser.db";
    public static final String TABLE_NAME = "classDetails";
    public static final String COL_1 = "id";
    public static final String C0L_2 = "username";
    public static final String COL_3 = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "( id INTEGER PRIMARY KEY  AUTOINCREMENT , username TEXT, pssword TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
       onCreate(db);
    }


}
