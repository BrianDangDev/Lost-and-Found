package com.example.a71;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a71.AdvertContract;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "adverts.db";

    private static final String SQL_CREATE_ADVERTS_TABLE =
            "CREATE TABLE " + AdvertContract.AdvertEntry.TABLE_NAME + " (" +
                    AdvertContract.AdvertEntry._ID + " INTEGER PRIMARY KEY," +
                    AdvertContract.AdvertEntry.COLUMN_POST_TYPE + " TEXT," +
                    AdvertContract.AdvertEntry.COLUMN_NAME + " TEXT," +
                    AdvertContract.AdvertEntry.COLUMN_PHONE_NUMBER + " TEXT," +
                    AdvertContract.AdvertEntry.COLUMN_DESCRIPTION + " TEXT," +
                    AdvertContract.AdvertEntry.COLUMN_DATE + " TEXT," +
                    AdvertContract.AdvertEntry.COLUMN_LOCATION + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ADVERTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
}
