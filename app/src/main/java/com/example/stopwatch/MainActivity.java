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
    public static final String KEY_ISRUNNING = "If It's Running";
    private Button start, restart;
    private Chronometer timer;
    private long current, pause, base, difference;
    private boolean boo, isRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        isRunning = true;
        Log.d(TAG, "onCreate: ");
        if(savedInstanceState != null) {
            timer.setBase(savedInstanceState.getLong(KEY_CHRONOMETER_BASE));
            isRunning = !savedInstanceState.getBoolean(KEY_ISRUNNING);
            if(!isRunning){//if is running
                difference = current - pause;
                timer.setBase(difference);
                start.setText(R.string.start);
                timer.stop();
                isRunning = true;
            }
            else{
                current = SystemClock.elapsedRealtime();
                if(!boo){
                    boo = true;
                    difference = 0;
                }
                start.setText(getString(R.string.pause));
                base = timer.getBase();
                timer.setBase(base + difference);
                timer.start();
                isRunning = false;
            }
        }
    }

    private void setListeners() {
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(isRunning)
                {
                    current = SystemClock.elapsedRealtime();
                    difference = current - pause;
                    if(!boo){
                        boo = true;
                        difference = 0;
                    }
                    start.setText(getString(R.string.pause));
                    base = timer.getBase();
                    timer.setBase(base + difference);
                    timer.start();
                    isRunning = false;
                }
                else{
                    pause = SystemClock.elapsedRealtime();
                    start.setText(R.string.start);
                    timer.stop();
                    isRunning = true;
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                timer.setBase(SystemClock.elapsedRealtime());
                pause = SystemClock.elapsedRealtime();
                current = SystemClock.elapsedRealtime();
                isRunning = true;
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
        outState.putBoolean(KEY_ISRUNNING, isRunning);
    }

}
