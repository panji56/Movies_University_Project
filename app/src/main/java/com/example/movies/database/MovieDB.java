package com.example.movies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movies.data.Movies;

import java.util.ArrayList;

public class MovieDB {
    private Context context;
    private DBHelper dbHelper;
    public MovieDB(Context context){
        this.context=context;
        dbHelper=new DBHelper(this.context);
    };

    //INSERT

    public void movieInsert(Movies movie){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //Cursor cursor = db.update();
            ContentValues cv=new ContentValues();
            cv.put("imdbID",movie.imdbID);
            cv.put("Title",movie.Title);
            cv.put("Year",movie.Year);
            cv.put("poster",movie.poster);
            db.insert(dbHelper.movieTable,null,cv);
        db.close();
    };
    //READ AREA

    public int movieCount(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Movies> user=new ArrayList<Movies>();
        Cursor cursor = db.query(dbHelper.movieTable,
                null,
                null,
                null,
                null,
                null,
                null);
        int count=cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }
    public ArrayList<Movies> movieRead(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Movies> user=new ArrayList<Movies>();
        Cursor cursor = db.query(dbHelper.movieTable,
                null,
                null,
                null,
                null,
                null,
                null);
        while(cursor.moveToNext()){
            Movies usr=new Movies();
            usr.imdbID=cursor.getString(cursor.getColumnIndex("imdbID"));
            usr.Title=cursor.getString(cursor.getColumnIndex("Title"));
            usr.Year=cursor.getString(cursor.getColumnIndex("Year"));
            usr.poster=cursor.getString(cursor.getColumnIndex("poster"));
            user.add(usr);
        }
        cursor.close();
        db.close();
        return user;
    };

    public ArrayList<Movies> movieRead(String id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Movies> user=new ArrayList<Movies>();
        Cursor cursor = db.query(dbHelper.movieTable,
                null,
                "imdbID=?",
                new String[]{id},
                null,
                null,
                null);
        while(cursor.moveToNext()){
            Movies usr=new Movies();
            usr.imdbID=cursor.getString(cursor.getColumnIndex("imdbID"));
            usr.Title=cursor.getString(cursor.getColumnIndex("Title"));
            usr.Year=cursor.getString(cursor.getColumnIndex("Year"));
            usr.poster=cursor.getString(cursor.getColumnIndex("poster"));
            user.add(usr);
        }
        cursor.close();
        db.close();
        return user;
    };

    //UPDATE AREA


    //DELETE AREA

    public void movieDelete(String id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(dbHelper.movieTable,"imdbID=?",new String[]{id});
        db.close();
    };
}
