package com.ivan.timetracker_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;


public class SaveActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        long timeStopped = MainActivity.recSysTime;
        long time = timeStopped - MainActivity.timer.getBase();

        long milli = time;
        long second = (time / 1000) % 60;
        long minute = (time / (1000 * 60)) % 60;
        long hour = (time / (1000 * 60 * 60)) % 24;

        String fTime = String.format("%02d:%02d:%02d:%02d", hour, minute, second, milli);

        TextView textView = (TextView) findViewById(R.id.time_display);
        textView.setText(fTime);
    }
}
