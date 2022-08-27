package com.example.qlsv.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.qlsv.Features.CreateStudentInfo.Student;
import com.example.qlsv.utils.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseQueryClass {
    private Context context;

    public DatabaseQueryClass(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertStudent (Student student) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        long id = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());
        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_STUDENT, null, contentValues);
        }catch (SQLException e){
            Logger.d("Exception : " + e.getMessage());
            Toast.makeText(context, "Operation failed " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }


    public List<Student> getAllStudent(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Student> studentList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                        long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION));
                        String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL));
                        String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE));

                        studentList.add(new Student(id, name, registrationNumber, email, phone));
                    }   while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }


}
