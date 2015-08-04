package com.ivan.timetracker_v1;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends ActionBarActivity{
    public static Chronometer timer;
    public static long timeWhenStopped = 0;
    public static long recSysTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (Chronometer) findViewById(R.id.timer);
        Button submitButton = (Button) findViewById(R.id.submit_button);
        final ToggleButton playPause = (ToggleButton) findViewById(R.id.playPause_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_play_black_48dp));
                playPause.setChecked(false);
                timer.stop();

                Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                startActivity(intent);
            }
        });

        playPause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//                Log.d("TAG", "Time before IF: " + timer.getBase() + "----"
//                            + SystemClock.elapsedRealtime() + "----"
//                            + timeWhenStopped);
                if (isChecked) {
                    //play
                    playPause.setText(null);
                    playPause.setTextOn(null);
                    playPause.setTextOff(null);
                    playPause.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_pause_black_48dp));
                    timer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
//                    Log.d("TAG", "Time on Play: " + timer.getBase() + "----"
//                            +  SystemClock.elapsedRealtime() + "----"
//                            + timeWhenStopped);
                    timer.start();
                } else {
                    //pause
                    playPause.setText(null);
                    playPause.setTextOn(null);
                    playPause.setTextOff(null);
                    playPause.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_play_black_48dp));
                    recSysTime = SystemClock.elapsedRealtime();
                    timeWhenStopped = timer.getBase() - recSysTime;
//                    Log.d("TAG", "Time on Pause: " + timer.getBase() + "----"
//                            +  recSysTime + "----"
//                            + timeWhenStopped);
                    timer.stop();
                }
//                Log.d(TAG, "Time after IF: " + timer.getBase() + "----" +
//                        SystemClock.elapsedRealtime() + "----"
//                        + timeWhenStopped);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
