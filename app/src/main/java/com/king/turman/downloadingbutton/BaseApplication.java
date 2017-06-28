package com.king.turman.downloadingbutton;

import android.app.Application;
import android.util.Log;

/**
 * Created by diaoqf on 2017/6/28.
 */

public class BaseApplication extends Application {

    private int current_process = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        current_process++;
        Log.i(getClass().getName(),"Application create...");
    }

    public int getCurrent_process() {
        return current_process;
    }

    public void setCurrent_process(int current_process) {
        this.current_process = current_process;
    }
}
