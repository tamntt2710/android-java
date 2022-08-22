package com.example.chapter_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    Button button;
    Button nextSceen;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 myAsyncTask.execute();
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPreExecute() {
            progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            textView.setText(values + "%");
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void unused) {
            // end thread
            super.onPostExecute(unused);
         //   progressBar.setProgress(100);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0 ;i <= 100 ; i++) {
                SystemClock.sleep(10);
                progressBar.setProgress(i);
                textView.setText(i + "%");
            }
            return null;
        }
    }



    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = findViewById(R.id.tvPercent);
        button = findViewById(R.id.btnStart);
        nextSceen = findViewById(R.id.switchScreen);
        myAsyncTask = new MyAsyncTask();
    }
}