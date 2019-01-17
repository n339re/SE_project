package com.example.user.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHandler extends  SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "hospital.db";
    private static SQLiteDatabase database;


    public DBHandler(Context context, String name, CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DBHandler(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE.CREATE_TABLE);
        db.execSQL(TABLE.CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE.TABLE_NAME2);
        onCreate(db);
    }




}
