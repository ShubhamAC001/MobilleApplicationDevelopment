package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.Locale;

public class MainActivity extends Activity {
    // Number of a seconds displayed on the stopwatch
    private int seconds = 0;

    // Judge if the stopwatch running
    private boolean running;

    // record whether program was running before onStop() is invoked.
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            // Restore the activity's state by getting values from the Bundle.
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state for the variables in the activity's onSaveInstanceState() method.
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

//    @Override
//    protected void onStop() {
//       super.onStop();
//       wasRunning = running;
//       running = false;
//    }

    // onPause() method is called irrespective of whether the activity is paused or stopped.
    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    // Implement the onStart() method.
    // If the stopwatch was running, set it running again.
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(wasRunning){
//            running = true;
//        }
//    }

    // onResume method is called irrespective of whether the activity is resumed or started.
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    // Start the Stopwatch running when the Start button is clicked
    public void onClickStart(View view){
        // start the stopwatch
        running = true;
    }

    // Stop the stopwatch
    public void onClickStop(View view){
        running = false;
    }

    // Reset the stopwatch when Reset button is clicked
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        // Get the TextView
        final TextView timeView = (TextView) findViewById(R.id.time_view);

        // Create a new Handler
        final Handler handler = new Handler();

        // Call the post() method, passing in a new Runnable.
        // The post() method process code without a delay,
        // so the code in the Runnable will run almost immediately.
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Format the seconds into hours, minutes and seconds. this is plain java code.
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                // set the text view text.
                timeView.setText(time);

                // if running is true, the increment the seconds variable
                if(running){
                    seconds++;
                }

                // Post the code in the Runnable to be run again after a delay of 1000 milliseconds.
                // As this line is included in the Runnable run() method
                // It will keep getting called.
                handler.postDelayed(this, 1000);
            }
        });
    }
}
