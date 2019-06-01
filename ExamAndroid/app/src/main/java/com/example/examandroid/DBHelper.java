package com.example.examandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "USER";
    public static final int DB_VERSION = 1;

    public static String TABLE_NAME = "TBL_Product";
    public static String ID = "id";
    public static String NAME = "name";
    public static String QUANTITY = "quantity";

    public DBHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY, " +
                NAME + "  TEXT, " +
                QUANTITY + " TEXT) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public String addUser(String user, String gender, String des){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user);
        contentValues.put(QUANTITY, gender);
        long isAdd = db.insert(TABLE_NAME, null, contentValues);
        if (isAdd == -1){
            return "Add fail";
        }
        db.close();
        return "Add Success";
    }
    public Cursor getAllUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM " +TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }
}
