package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_CHRONOMETER_BASE = "chronometer base";
    private Button start, restart;
    private Chronometer timer;
    private int startClick;
    private long current, pause, base, difference;
    private boolean boo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        Log.d(TAG, "onCreate: ");
        if(savedInstanceState != null) {
            timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
            if(startClick % 2 == 0){//if is paused
                pause = SystemClock.elapsedRealtime();
                start.setText(R.string.start);
                timer.stop();
                startClick++;
            }
            else{
                current = SystemClock.elapsedRealtime();
                difference = current - pause;
                if(!boo){
                    boo = true;
                    difference = 0;
                }
                start.setText(getString(R.string.pause));
                startClick++;
                base = timer.getBase();
                timer.setBase(base + difference);
                timer.start();
            }
        }
    }

    private void setListeners() {
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(startClick % 2 == 0)
                {
                    current = SystemClock.elapsedRealtime();
                    difference = current - pause;
                    if(!boo){
                        boo = true;
                        difference = 0;
                    }
                    start.setText(getString(R.string.pause));
                    startClick++;
                    base = timer.getBase();
                    timer.setBase(base + difference);
                    timer.start();
                }
                else{
                    pause = SystemClock.elapsedRealtime();
                    start.setText(R.string.start);
                    timer.stop();
                    startClick++;
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                timer.setBase(SystemClock.elapsedRealtime());
                pause = SystemClock.elapsedRealtime();
                current = SystemClock.elapsedRealtime();
                timer.stop();
                start.setText(getString(R.string.start));
                if(startClick % 2 != 0) {
                    startClick++;
                }
            }
        });


    }

    private void wireWidgets() {
        start = findViewById(R.id.button_main_start);
        restart = findViewById(R.id.button2_main_restart);
        timer = findViewById(R.id.chronometer_main_time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_CHRONOMETER_BASE, timer.getBase());
    }

}
