package com.aty.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ListView listView;
    Button startB, stopB, resetB,lapB;
    TextView timerB;
    Handler handler;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.timesloop);
        startB = findViewById(R.id.start);
        stopB = findViewById(R.id.stop);
        resetB = findViewById(R.id.reset);
        timerB = findViewById(R.id.timer);
        lapB = findViewById(R.id.lap);
        handler = new Handler();


        startB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                resetB.setEnabled(false);
                startB.setEnabled(false);
            }
        });

        stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);
            resetB.setEnabled(true);
            startB.setEnabled(true);
            }
        });
        lapB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        resetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;

                timerB.setText("00:00:00");
                startB.setEnabled(true);

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Long.toString(UpdateTime));
        arrayList.add("asdasd");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            timerB.setText("" + Minutes + ":" + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };

}
