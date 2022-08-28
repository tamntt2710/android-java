package com.example.movie_content_provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Movie {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    public final static String MOVIE_TABLE = "favorite_movie";
    public final static String MOVIE_NAME = "name";
    public final static String MOVIE_DESCRIPTION = "description";
    public final static String MOVIE_ID = "_id";

    public Movie(Context context){
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long createMovie(String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_NAME, name);
        contentValues.put(MOVIE_DESCRIPTION, desc);
        return database.insert(MOVIE_TABLE, null, contentValues);

    }

    public void createMovie(ContentValues contentValues){
        database.insert(MOVIE_TABLE, null, contentValues);
    }

    public int deleteAll() {
        return database.delete(MOVIE_TABLE, null, null);
    }

    public int updateMovie(ContentValues values, String id) {
        return database.update(MOVIE_TABLE, values, MOVIE_ID + " = ?", new String[]{});
    }

    public int updateMovie(ContentValues values, String selection, String[] selecSrg0) {
        return database.update(MOVIE_TABLE, values, selection, selecSrg0);

    }


}
