package com.king.turman.downloadingbutton;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diaoqf on 2017/6/28.
 */

public class WorkingService extends Service {

    private NotificationManager mNotificationManager;
    private boolean mCanRun = true;
    private List<Book> mBooks = new ArrayList<>();

    private final IMyService.Stub mBinder = new IMyService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBook() throws RemoteException {
            synchronized (mBooks) {
                return mBooks;
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (mBooks) {
                if (!mBooks.contains(book)) {
                    mBooks.add(book);
                }
            }
        }

        //过滤等
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        Thread thread = new Thread(null,new MyRunner(), "BackgroundService");
        thread.start();

        synchronized (mBooks) {
            for (int i = 1; i < 6; i++) {
                Book book = new Book(i,"Book"+i,i*20);
                mBooks.add(book);
            }
        }
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("BackgroundService", String.format("on bind,intent = %s", intent.toString()));
        displayNotificationMessage("服务已启动");
        return mBinder;
    }

    private void displayNotificationMessage(String message)
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        mBuilder.setContentTitle("我的通知")
                .setContentText(message)
                .setPriority(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setContentIntent(contentIntent);
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void onDestroy()
    {
        mCanRun = false;
        super.onDestroy();
    }

    class MyRunner implements Runnable {

        private int counter;

        @Override
        public void run() {
            while (mCanRun) {
                counter++;
                Log.i("Service-thread",counter+"");
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}



































