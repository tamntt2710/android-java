package com.example.chapter_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    Button button;
    Handler handler;
     static boolean isRunning = false;
     Button nextSceen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // logic
              if(isRunning == false) {
                  doStart();
              }
            }
        });

        handler = new Handler(){
            public void handleMessage(Message message) {
                super.handleMessage(message);
                progressBar.setProgress(message.arg1);
                textView.setText(message.arg1 + "%");

            }
        };

        nextSceen.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void doStart() {
        progressBar.setProgress(0);
       isRunning = false;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i<= 100 && isRunning ; i++){
                    SystemClock.sleep(1000);
                    Message message = handler.obtainMessage();
                    message.arg1 = i;
                    handler.sendMessage(message);
                }
            }
        });
        isRunning = true;
        thread.start();
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = findViewById(R.id.tvPercent);
        button = findViewById(R.id.btnStart);
        nextSceen = findViewById(R.id.switchScreen);
    }

}