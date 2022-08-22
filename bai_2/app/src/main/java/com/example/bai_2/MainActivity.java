package com.example.bai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView textView;
    ListView randomList;
    ListView sntList;
    Handler handler1;
    Handler handler2;
    ImageButton imageButton;
//    String tutorials[]
//            = { "Algorithms", "Data Structures",
//            "Languages", "Interview Corner",
//            "GATE", "ISRO CS",
//            "UGC NET CS", "CS Subjects",
//            "Web Technologies" };
    List<Integer> randomListNumber;
    List<Integer> sntListNumber;
    Integer n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = textView.getText().toString();
                n = Integer.parseInt(input);
                randomList(n);
            }
        });
        randomListNumber = new ArrayList<Integer>();
        sntListNumber = new ArrayList<Integer>();

        handler1 = new Handler(){
            public void handleMessage(Message message) {
                super.handleMessage(message);
                randomListNumber.add(message.arg1);
                if(sortSNT(message.arg1)){
                    sntListNumber.add(message.arg1);
                };
                showInListRamdom(randomList, randomListNumber);
                showInListRamdom(sntList, sntListNumber);

            }
        };

    }

    private boolean sortSNT(int n ) {
        if(n == 1){
            return false;
        } else {
            for( int i = 2; i < Math.sqrt(n) ; i++) {
                if(n % i == 0) return false;
            }
            return true;
        }
    }


    private void showInListRamdom(ListView listView, List<Integer> listNumber){
        ArrayAdapter<Integer> arr;
        arr
                = new ArrayAdapter<Integer>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listNumber);
        listView.setAdapter(arr);
    }

    private void randomList(Integer n) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
              for(int i = 0; i< n ; i ++) {
                  final int random = new Random().nextInt(100);
                  Message message = handler1.obtainMessage();

                  message.arg1 = random;
                  handler1.sendMessage(message);

              }
            }
        });
        thread.start();
    }

    private void onInit() {
        textView = findViewById(R.id.edtNumber);
        startButton = findViewById(R.id.startButton);
        randomList = findViewById(R.id.randomList);
        sntList = findViewById(R.id.SNTlist);
    }
}