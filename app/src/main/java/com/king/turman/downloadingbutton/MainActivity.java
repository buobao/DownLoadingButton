package com.king.turman.downloadingbutton;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.king.turman.library.DownloadButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final double[] currentPercent = {0.0};
        final DownloadButton db = (DownloadButton) findViewById(R.id.loading_btn);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                db.setCurrentPercent(currentPercent[0]);
            }
        };

        Timer timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                currentPercent[0] += 0.05;
                handler.sendEmptyMessage(0);
            }
        };

        timer.schedule(timerTask,500,200);
    }
}
