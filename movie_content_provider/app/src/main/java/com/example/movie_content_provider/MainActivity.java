package com.example.movie_content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView lb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnInset = findViewById(R.id.btnInsert);
        btnInset.setOnClickListener(this);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        Button btnDisplay = findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(this);
        Button btnDisplayOne = findViewById(R.id.btnDisplayOne);
        btnDisplayOne.setOnClickListener(this);
        lb1 = findViewById(R.id.content);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                deleteAll();
                break;
            case R.id.btnDisplay:
                getAllData();
                break;
            case R.id.btnInsert:
                insertData();
                break;
        }

    }

    private void insertData() {
//        Uri uri = Uri.parse(ContentProvider.CONTENT_URI + "/object");
//        ContentResolver resolver = getContentResolver();
//        ContentValues newContent = new ContentValues();
//        newContent.put(Movie.MOVIE_NAME,"name");
//        newContent.put(Movie.MOVIE_DESCRIPTION,"desc");
//        resolver.insert(uri, newContent);
//        Toast.makeText(this, "Created Contact ", Toast.LENGTH_LONG).show();
        insertContact("book-", "description");

        /*
        *  ContentValues values = new ContentValues();
        values.put(DBOpenHelper.CONTACT_NAME, contactName);
        values.put(DBOpenHelper.CONTACT_PHONE, contactPhone);
        Uri contactUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
        Toast.makeText(this, "Created Contact " + contactName, Toast.LENGTH_LONG).show();

        * */
    }

    private void insertContact(String contactName, String contactPhone) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.CONTACT_NAME, contactName);
        values.put(DatabaseHelper.CONTACT_PHONE, contactPhone);
        Uri contactUri = getContentResolver().insert(ContentProvider.CONTENT_URI, values);
        Toast.makeText(this, "Created Contact " + contactName, Toast.LENGTH_LONG).show();
    }


    private void getAllData() {
        Uri uri = ContentProvider.CONTENT_URI;
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if(cursor == null ){
            System.out.println("cursor null");
        } else {
            int nameIndex = cursor.getColumnIndex(DatabaseHelper.CONTACT_NAME);
            int valueIndex = cursor.getColumnIndex(DatabaseHelper.CONTACT_PHONE);
            String str = "";
            cursor.moveToFirst();
            while (!cursor.isAfterLast() ) {
                String name = cursor.getString(nameIndex);
                String value = cursor.getString(valueIndex);
                str += name +" " +value + " \n";
                cursor.moveToNext();
            }
            lb1.setText(str);
        }

    }

    private void deleteAll() {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}