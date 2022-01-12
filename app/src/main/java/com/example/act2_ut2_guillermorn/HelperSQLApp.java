package com.example.act2_ut2_guillermorn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class HelperSQLApp extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbTheFruitis.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS fruitis(id integer PRIMARY KEY AUTOINCREMENT, nombre text, peso  integer, sabor text, podrida boolean)";

    public HelperSQLApp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS fruitis");

        db.execSQL(SQL_CREATE_ENTRIES);
    }
}
