package com.example.movies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME="MovieDB";
    private static final int DB_version=1;
    public String movieTable="movieTable";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser="CREATE TABLE IF NOT EXISTS "
                +movieTable
                +" ("
                +"imdbID TEXT"
                +",Title TEXT"
                +",Year TEXT"
                +",poster TEXT)";
        db.execSQL(createUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String q1="DROP TABLE IF EXISTS "+movieTable;
        db.execSQL(q1);
        onCreate(db);
    }
}
