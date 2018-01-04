package com.example.madhav.attendance_marker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Madhav on 04-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "score.db";
    private SQLiteDatabase myDataBase;
    private Context myContext = null;
    public static final String TABLE_NAME="Clientinfo";
    public static final String Column_fname="fname";
    public static final String Column_lname="lname";
    public static final String Column_contact="contact";
    public static final String Column_dob="dob";
    public static final String Column_email="email";
    public static final String Column_pass="pass";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
