package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Button start, restart;
    private Chronometer timer;
    private int startClick;
    private long base, current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        Log.d(TAG, "onCreate: ");
        startClick = 0;
    }

    private void setListeners() {
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(startClick % 2 == 0)
                {
                    start.setText("pause");
                    timer.start();
                    startClick++;
                    base = timer.getBase();
                }
                else{
                    start.setText("start");
                    timer.stop();
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
}
