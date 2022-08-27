package com.example.qlsv.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.example.qlsv.utils.Config;

import com.orhanobut.logger.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create tables SQL execution
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Config.TABLE_STUDENT + "("
                + Config.COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_STUDENT_REGISTRATION + " INTEGER NOT NULL UNIQUE, "
                + Config.COLUMN_STUDENT_PHONE + " TEXT,"
                + Config.COLUMN_STUDENT_EMAIL + " TEXT "
                + ")";
        Logger.d("Table create SQL: " + CREATE_STUDENT_TABLE);

        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);

        Logger.d("DB created!");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // drop table if exists

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_STUDENT);

        // create table again

        onCreate(sqLiteDatabase);

    }
}
